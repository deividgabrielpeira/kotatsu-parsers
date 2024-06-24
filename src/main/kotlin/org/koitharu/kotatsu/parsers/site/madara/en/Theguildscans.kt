package org.koitharu.kotatsu.parsers.site.madara.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("THEGUILDSCANS", "TheGuildScans", "en")
internal class Theguildscans(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.THEGUILDSCANS, "theguildscans.com") {
	override val postReq = true
}
