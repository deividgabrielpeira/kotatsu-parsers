package org.koitharu.kotatsu.parsers.site.mangareader.id

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("SHEAKOMIK", "SheaKomik", "id")
internal class Sheakomik(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.SHEAKOMIK, "sheakomik.com", pageSize = 40, searchPageSize = 40) {
	override val datePattern = "MMM d, yyyy"
	override val isTagsExclusionSupported = false
}
