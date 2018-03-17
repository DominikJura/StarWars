package co.uk.androidrecruitmenttask.feature.main

import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.feature.common.BaseContract

interface MainActivityContract {

    interface View {
        var isPageLoading: Boolean
        var isAllPagesLoaded: Boolean
        var nextPageIndex: Int

        fun addPeopleToList(peopleList: List<People>)
        fun showSnackBar(errorMessage: String?)
    }

    interface Router

    interface Presenter : BaseContract.Presenter {
        fun fetchPeopleFromRemote()
    }
}