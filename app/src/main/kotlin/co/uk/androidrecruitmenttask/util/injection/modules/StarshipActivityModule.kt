package co.uk.androidrecruitmenttask.util.injection.modules

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract
import co.uk.androidrecruitmenttask.feature.startships.presentation.StarshipActivityPresenter
import co.uk.androidrecruitmenttask.feature.startships.ui.StarshipsActivity
import co.uk.androidrecruitmenttask.feature.startships.ui.adapters.StarshipsAdapter
import co.uk.androidrecruitmenttask.util.repository.Repository
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
    fun starshipAdapter(): StarshipsAdapter =
            StarshipsAdapter()

    @Provides
    fun presenter(
            view: StarshipActivityContract.View,
            repository: Repository,
            compositeDisposable: CompositeDisposable
    ): StarshipActivityContract.Presenter =
            StarshipActivityPresenter(view, repository, compositeDisposable)
}