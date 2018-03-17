package co.uk.androidrecruitmenttask.util.repository

import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.util.tools.StarWarsParser

import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject

class RepositoryImpl(
        private val starWarsService: StarWarsService,
        private val starWarsParser: StarWarsParser
): Repository {

    override fun getPeople(): Single<ListResponse<People>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStarships(starshipsUrlList: List<String>): Single<List<Starships>> =
            BehaviorSubject.just(starshipsUrlList)
                .flatMapIterable { it }
                .map { starWarsParser.getStarshipId(it) }
                .toList()
                .toObservable()
                .flatMapIterable { it }
                .flatMapSingle { starWarsService.getStarshipById(it) }
                .toList()

}