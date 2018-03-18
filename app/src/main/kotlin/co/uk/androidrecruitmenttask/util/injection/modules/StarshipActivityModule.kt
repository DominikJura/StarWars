package co.uk.androidrecruitmenttask.util.injection.modules

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract
import co.uk.androidrecruitmenttask.feature.startships.presentation.StarshipActivityPresenter
import co.uk.androidrecruitmenttask.feature.startships.ui.StarshipsActivity
import co.uk.androidrecruitmenttask.feature.startships.ui.adapters.StarshipsAdapter
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class StarshipActivityModule {

    @Provides
    fun view(activity: StarshipsActivity): StarshipActivityContract.View =
            activity

    @Provides
    fun recyclerLayoutManager(activity: StarshipsActivity): RecyclerView.LayoutManager =
            LinearLayoutManager(activity)

    @Provides
    fun starshipList(): ArrayList<Starships> =
            arrayListOf()

    @Provides
    fun starshipAdapter(starshipList: ArrayList<Starships>): StarshipsAdapter =
            StarshipsAdapter(starshipList)

    @Provides
    fun presenter(
        view: StarshipActivityContract.View,
        repository: Repository,
        httpErrorProvider: HttpErrorProvider,
        compositeDisposable: CompositeDisposable
    ): StarshipActivityContract.Presenter =
            StarshipActivityPresenter(view, repository, httpErrorProvider, compositeDisposable)
}