package org.koitharu.kotatsu.parsers.site.madara.fr

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("HENTAISCANTRADVF", "Hentai-Scantrad", "fr")
internal class HentaiScantradVf(context: MangaLoaderContext) :
	MadaraParser(context, MangaSource.HENTAISCANTRADVF, "hentai.scantrad-vf.cc") {

	override val isNsfwSource = true
	override val datePattern = "d MMMM, yyyy"
}