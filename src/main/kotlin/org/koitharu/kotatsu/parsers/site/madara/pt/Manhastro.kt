package org.koitharu.kotatsu.parsers.site.madara.pt

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("MANHASTRO", "Manhastro", "pt")
internal class Manhastro(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.MANHASTRO, "manhastro.com", 18)
