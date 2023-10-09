package org.koitharu.kotatsu.parsers.site.en

import androidx.collection.ArraySet
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.jsoup.nodes.Element
import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.PagedMangaParser
import org.koitharu.kotatsu.parsers.config.ConfigKey
import org.koitharu.kotatsu.parsers.model.*
import org.koitharu.kotatsu.parsers.util.*
import java.util.*

@MangaSourceParser("PURURIN", "Pururin", "en", ContentType.HENTAI)
internal class Pururin(context: MangaLoaderContext) :
	PagedMangaParser(context, MangaSource.PURURIN, pageSize = 20) {

	override val sortOrders: Set<SortOrder> =
		EnumSet.of(SortOrder.UPDATED, SortOrder.POPULARITY, SortOrder.RATING, SortOrder.ALPHABETICAL)

	override val configKeyDomain = ConfigKey.Domain("pururin.to")

	override suspend fun getListPage(
		page: Int,
		query: String?,
		tags: Set<MangaTag>?,
		sortOrder: SortOrder,
	): List<Manga> {
		val tag = tags.oneOrThrowIfMany()
		val url = buildString {
			append("https://")
			append(domain)
			if (!query.isNullOrEmpty()) {
				append("/search?q=")
				append(query.urlEncoded())
				append("&page=")
				append(page)
			} else {
				append("/browse")
				if (!tags.isNullOrEmpty()) {
					append("/tags/content/")
					append(tag?.key.orEmpty())
					append("/")
				}
				append("?page=")
				append(page)
				append("&sort=")
				when (sortOrder) {
					SortOrder.UPDATED -> append("")
					SortOrder.POPULARITY -> append("most-viewed")
					SortOrder.RATING -> append("highest-rated")
					SortOrder.ALPHABETICAL -> append("title")
					else -> append("")
				}
			}
		}
		val doc = webClient.httpGet(url).parseHtml()
		return doc.select(".row-gallery a.card-gallery").map { a ->
			val href = a.attrAsRelativeUrl("href")
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href.toAbsoluteUrl(domain),
				coverUrl = a.selectFirst("img.card-img-top")?.src().orEmpty(),
				title = a.selectFirst(".title")?.text().orEmpty(),
				altTitle = null,
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				author = null,
				state = null,
				source = source,
				isNsfw = isNsfwSource,
			)
		}
	}

	override suspend fun getTags(): Set<MangaTag> {
		val root = webClient.httpGet("https://$domain/tags/content").parseHtml()
		val totalPagesTags = root.select("ul.pagination .page-item").dropLast(1).last().text().toInt()
		return coroutineScope {
			(1..totalPagesTags).map { page ->
				async { getTags(page) }
			}
		}.awaitAll().flattenTo(ArraySet(360))
	}

	private suspend fun getTags(page: Int): Set<MangaTag> {
		val url = "https://$domain/tags/content?page=$page"
		val root = webClient.httpGet(url).parseHtml()
		return root.parseTags()
	}

	private fun Element.parseTags() = select("table tr td a").mapToSet {
		val href = it.attr("href").substringAfterLast("content/").substringBeforeLast('/')
		MangaTag(
			key = href,
			title = it.text(),
			source = source,
		)
	}

	override suspend fun getDetails(manga: Manga): Manga = coroutineScope {
		val fullUrl = manga.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		manga.copy(
			description = doc.selectFirst("p.mb-2")?.text().orEmpty(),
			rating = doc.selectFirst("td span.rating")?.attr("content")?.toFloatOrNull()?.div(5f) ?: RATING_UNKNOWN,
			tags = doc.body().select("tr:contains(Contents) ul.list-inline a").mapNotNullToSet {
				val href = it.attr("href").substringAfterLast("content/").substringBeforeLast('/')
				MangaTag(
					key = href,
					title = it.text(),
					source = source,
				)
			},
			author = doc.selectFirst("a[itemprop=author]")?.text(),
			chapters = listOf(
				MangaChapter(
					id = manga.id,
					name = manga.title,
					number = 1,
					url = manga.url,
					scanlator = null,
					uploadDate = 0,
					branch = null,
					source = source,
				),
			),
		)
	}

	override suspend fun getRelatedManga(seed: Manga): List<Manga> {
		val doc = webClient.httpGet(seed.url.toAbsoluteUrl(domain)).parseHtml()
		val root = doc.body().selectFirstOrThrow(".row-gallery-small")
		return root.select("a.card-gallery").mapNotNull { a ->
			val href = a.attrAsRelativeUrl("href")
			Manga(
				id = generateUid(href),
				url = href,
				publicUrl = href.toAbsoluteUrl(domain),
				coverUrl = a.selectFirst("img.card-img-top")?.src().orEmpty(),
				title = a.selectFirst(".title")?.text().orEmpty(),
				altTitle = null,
				rating = RATING_UNKNOWN,
				tags = emptySet(),
				author = null,
				state = null,
				source = source,
				isNsfw = false,
			)
		}
	}

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> {
		val fullUrl = chapter.url.toAbsoluteUrl(domain)
		val doc = webClient.httpGet(fullUrl).parseHtml()
		return doc.select(".gallery-preview img").map { url ->
			val img = url.src()?.toRelativeUrl(domain) ?: url.parseFailed("Image src not found")
			val urlImage = img.replace("t.", ".")
			MangaPage(
				id = generateUid(urlImage),
				url = urlImage,
				preview = null,
				source = source,
			)
		}
	}
}