package co.uk.androidrecruitmenttask.util.repository

import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.main.MainScreenTest.Companion.MOCK_PEOPLE_LIST_SIZE
import co.uk.androidrecruitmenttask.main.robot.MainRobot.Companion.STAR_WARS_JEDI_TEXT
import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.util.tools.StarWarsParser
import io.reactivex.Observable
import io.reactivex.Single

class RepositoryImpl(
        private val starWarsService: StarWarsService,
        private val starWarsParser: StarWarsParser
) : Repository {

    companion object {
        private const val MOCK_PEOPLE_STARSHIPS_LIST_SIZE = 0
        private const val STARSHIP_TEXT = "starship"
        private const val NEXT_LINK_TEXT = "someNextLink"
    }

    override fun getPeople(page: Int): Single<ListResponse<People>> =
            Single.just(getListResponse())

    private fun getListResponse(): ListResponse<People> =
            ListResponse<People>().apply {
                next = NEXT_LINK_TEXT
                results = getMockPeopleList()
            }

    private fun getMockPeopleList(): List<People> =
            List(MOCK_PEOPLE_LIST_SIZE, { People("$STAR_WARS_JEDI_TEXT $it", ArrayList(MOCK_PEOPLE_STARSHIPS_LIST_SIZE)) })

    override fun getStarships(starshipsUrlList: List<String>): Observable<Starships> =
        Observable.just(Starships(STARSHIP_TEXT))
}