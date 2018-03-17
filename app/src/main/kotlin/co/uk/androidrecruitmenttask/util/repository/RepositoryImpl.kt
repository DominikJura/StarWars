package co.uk.androidrecruitmenttask.util.repository

import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.util.tools.StarWarsParser
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RepositoryImpl(
        private val starWarsService: StarWarsService,
        private val starWarsParser: StarWarsParser
) : Repository {

    override fun getPeople(page: Int): Single<ListResponse<People>> =
            starWarsService.getPeople(page)
                    .subscribeOn(Schedulers.io())

    override fun getStarships(starshipsUrlList: List<String>): Observable<Starships> =
            PublishSubject.just(starshipsUrlList)
                    .flatMapIterable { it }
                    .map { starWarsParser.getStarshipId(it) }
                    .toList()
                    .toObservable()
                    .flatMapIterable { it }
                    .flatMapSingle { starWarsService.getStarshipById(it) }
}