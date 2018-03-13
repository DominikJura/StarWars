package co.uk.androidrecruitmenttask.util.injection.modules

import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.feature.main.presentation.MainActivityPresenter
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleRecyclerAdapter
import co.uk.androidrecruitmenttask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun peopleList(): MutableList<People> =
            arrayListOf()

    @Provides
    fun peopleAdapter(peopleList: MutableList<People>): PeopleRecyclerAdapter =
            PeopleRecyclerAdapter(peopleList)

    @RuntimeScope
    @Provides
    fun presenter(): MainActivityContract.Presenter =
            MainActivityPresenter()
}