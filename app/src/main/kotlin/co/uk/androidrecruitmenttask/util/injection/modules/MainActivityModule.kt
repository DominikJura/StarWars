package co.uk.androidrecruitmenttask.util.injection.modules

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.data.api.StarWarsService
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.feature.main.presentation.MainActivityPresenter
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleRecyclerAdapter
import co.uk.androidrecruitmenttask.util.configuration.ResourceProvider
import co.uk.androidrecruitmenttask.util.injection.RuntimeScope
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProviderImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View =
            activity

    @Provides
    fun peopleList(): ArrayList<People> =
            arrayListOf()

    @Provides
    fun peopleAdapter(peopleList: ArrayList<People>): PeopleRecyclerAdapter =
            PeopleRecyclerAdapter(peopleList)

    @Provides
    fun recyclerLayoutManager(activity: MainActivity): RecyclerView.LayoutManager =
            LinearLayoutManager(activity)

    @Provides
    fun httpErrorProvider(resourceProvider: ResourceProvider): HttpErrorProvider =
         HttpErrorProviderImpl(resourceProvider)

    @RuntimeScope
    @Provides
    fun presenter(
            view: MainActivityContract.View,
            service: StarWarsService,
            httpErrorProvider: HttpErrorProvider,
            compositeDisposable: CompositeDisposable
    ): MainActivityContract.Presenter =
            MainActivityPresenter(view, service, httpErrorProvider, compositeDisposable)
}