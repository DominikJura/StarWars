package co.uk.androidrecruitmenttask.util.repository

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.util.tools.StarWarsParser
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock

class RepositoryTest : BaseTest() {

    @Mock
    private lateinit var starWarsService: StarWarsService

    @Mock
    private lateinit var starWarsParser: StarWarsParser

    private lateinit var repository: Repository

    override fun setup() {
        super.setup()

        repository = RepositoryImpl(starWarsService, starWarsParser)
    }

    @Ignore
    @Test
    fun getPeople() {
        //TODO write test
    }

    @Ignore
    @Test
    fun getStarships() {
        //TODO write test
    }
}