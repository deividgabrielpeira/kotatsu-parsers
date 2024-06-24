package org.koitharu.kotatsu.parsers.site.mangareader.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("LUMINOUSSCANS", "LuminousScans", "en")
internal class LuminousScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.LUMINOUSSCANS, "lumitoon.com", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"
	override val isTagsExclusionSupported = false
}
