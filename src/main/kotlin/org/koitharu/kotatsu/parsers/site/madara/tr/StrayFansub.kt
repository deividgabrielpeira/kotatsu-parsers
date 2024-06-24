package org.koitharu.kotatsu.parsers.site.madara.tr

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.model.ContentType
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.site.madara.MadaraParser

@MangaSourceParser("STRAYFANSUB", "StrayFansub", "tr", ContentType.HENTAI)
internal class StrayFansub(context: MangaLoaderContext) :
	MadaraParser(context, MangaParserSource.STRAYFANSUB, "strayfansub.homes")
