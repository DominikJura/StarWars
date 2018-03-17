package co.uk.androidrecruitmenttask.util.injection.modules

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import co.uk.androidrecruitmenttask.data.api.People
import co.uk.androidrecruitmenttask.util.api.StarWarsService
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.feature.main.navigation.MainActivityRouter
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
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

@Module
class MainActivityModule {

    @Provides
    fun view(activity: MainActivity): MainActivityContract.View =
            activity

    @Provides
    fun router(activity: MainActivity): MainActivityContract.Router =
            MainActivityRouter(activity)

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

    @Provides
    fun onLoadMoreSubject(): Subject<Int> =
            PublishSubject.create()

    @RuntimeScope
    @Provides
    fun presenter(
            view: MainActivityContract.View,
            router: MainActivityContract.Router,
            service: StarWarsService,
            onLoadMoreSubject: Subject<Int>,
            resourceProvider: ResourceProvider,
            httpErrorProvider: HttpErrorProvider,
            compositeDisposable: CompositeDisposable
    ): MainActivityContract.Presenter =
            MainActivityPresenter(
                    view,
                    router,
                    service,
                    onLoadMoreSubject,
                    resourceProvider,
                    httpErrorProvider,
                    compositeDisposable
            )
}