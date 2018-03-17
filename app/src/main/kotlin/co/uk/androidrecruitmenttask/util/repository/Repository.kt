package co.uk.androidrecruitmenttask.util.repository

import co.uk.androidrecruitmenttask.data.api.ListResponse
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.data.api.Starships
import io.reactivex.Single

interface Repository {

    fun getPeople(): Single<ListResponse<People>>
    fun getStarships(starshipsUrlList: List<String>): Single<List<Starships>>
}