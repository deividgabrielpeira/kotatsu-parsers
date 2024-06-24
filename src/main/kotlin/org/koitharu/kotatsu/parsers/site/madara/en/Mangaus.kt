package org.koitharu.kotatsu.parsers.site.madara.en

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("MANGAUS", "Mangaus", "en")
internal class Mangaus(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANGAUS, "mangaus.xyz") {
	override val withoutAjax = true
}
