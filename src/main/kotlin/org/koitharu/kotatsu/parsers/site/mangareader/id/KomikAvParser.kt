package org.koitharu.kotatsu.parsers.site.mangareader.id

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.mangareader.MangaReaderParser

@MangaSourceParser("KOMIKAV", "KomikAv", "id")
internal class KomikAvParser(context: MangaLoaderContext) :
	MangaReaderParser(context, MangaParserSource.KOMIKAV, "komikav.com", pageSize = 20, searchPageSize = 10) {
	override val isTagsExclusionSupported = false
}
