package co.uk.androidrecruitmenttask.util.tools

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class StarWarsParserTest {

    private lateinit var starWarsParser: StarWarsParser

    @Before
    fun setup() {
        starWarsParser = StarWarsParserImpl()
    }

    @Test
    fun `should return proper id after getStarshipId is called`() {
        val starshipsURL1 = "https://someApi/api/starships/12/"
        val starshipsURL2 = "https://someApi/api/starships/22/"

        val expectedId1 = 12
        val expectedId2 = 22

        val id1 = starWarsParser.getStarshipId(starshipsURL1)
        val id2 = starWarsParser.getStarshipId(starshipsURL2)

        assertEquals(id1, expectedId1)
        assertEquals(id2, expectedId2)
    }

    @Test(expected = NoSuchElementException::class)
    fun `should throw exception after getStarshipId is called`() {
        val starshipsURL = "https://someApi/api/starships/noElement/"

        starWarsParser.getStarshipId(starshipsURL)
    }
}