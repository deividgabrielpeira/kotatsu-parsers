package org.koitharu.kotatsu.parsers.site.madara.pt

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.MangaSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser


@MangaSourceParser("MANGASEHRI", "Mangasehri", "tr")
internal class Mangasehri(context: MangaLoaderContext) :
	MadaraParser(context, MangaSource.MANGASEHRI, "mangasehri.com", 18) {
	override val datePattern = "dd/MM/yyyy"
}