package org.koitharu.kotatsu.parsers.site.mangareader.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("ARVENSCANS", "ArvenScans", "en")
internal class ArvenScans(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.ARVENSCANS, "arvenscans.org", pageSize = 20, searchPageSize = 10) {
	override val listUrl = "/series"
}
