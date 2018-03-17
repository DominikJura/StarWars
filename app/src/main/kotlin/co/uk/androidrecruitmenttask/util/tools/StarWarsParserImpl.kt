package co.uk.androidrecruitmenttask.util.tools

class StarWarsParserImpl : StarWarsParser {

    companion object {
        const val STARSHIP_ID_REGEX_PATTERN = "(?<=starships/)\\d+"
    }

    override fun getStarshipId(starshipUrl: String): Int {
        val regex = Regex(STARSHIP_ID_REGEX_PATTERN)
        val id = regex.find(starshipUrl)?.value

        return id?.toInt() ?: throw NoSuchElementException("fail to parse starship Id")
    }
}