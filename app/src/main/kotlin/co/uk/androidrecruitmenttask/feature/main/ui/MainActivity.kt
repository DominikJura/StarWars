package co.uk.androidrecruitmenttask.feature.main.ui

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.People
import co.uk.androidrecruitmenttask.feature.common.ui.BaseActivity
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.View
import co.uk.androidrecruitmenttask.feature.main.ui.adapters.PeopleRecyclerAdapter
import co.uk.androidrecruitmenttask.ui.util.StarWarsScrollListener
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.MAIN_KEY_ALL_PAGES_LOADED
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.MAIN_KEY_NEXT_PAGE_INDEX
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.MAIN_KEY_PEOPLE_LIST
import javax.inject.Inject

class MainActivity : BaseActivity<Presenter>(), View {

    companion object {
        const val FIRST_PAGE_NUMBER = 1
    }

    @BindView(R.id.main_people_recycler)
    lateinit var peopleRecyclerView: RecyclerView

    @Inject
    lateinit var peopleAdapter: PeopleRecyclerAdapter

    @Inject
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    override var isPageLoading: Boolean = false
    set(value) {
        field = value
        peopleAdapter.isLoading = value
    }

    override var isAllPagesLoaded: Boolean = false

    override var nextPageIndex: Int = FIRST_PAGE_NUMBER

    override val layoutId: Int = R.layout.activity_main

    override fun init() {
        initRecycler()
    }

    private fun initRecycler() = with(peopleRecyclerView) {
        layoutManager = recyclerLayoutManager
        adapter = peopleAdapter
        addOnScrollListener(object : StarWarsScrollListener() {
            override var isLoading: Boolean = isPageLoading
            override var isLastPage: Boolean = isAllPagesLoaded
            override fun onLoadMore() { presenter.fetchPeopleFromRemote() }
        })
        //TODO handel click
    }

    override fun onSaveInstanceState(outState: Bundle) = with(outState) {
        putInt(MAIN_KEY_NEXT_PAGE_INDEX, nextPageIndex)
        putBoolean(MAIN_KEY_ALL_PAGES_LOADED, isAllPagesLoaded)
        putParcelableArrayList(MAIN_KEY_PEOPLE_LIST, peopleAdapter.peopleList)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) = with(savedInstanceState) {
        nextPageIndex = getInt(MAIN_KEY_NEXT_PAGE_INDEX)
        isAllPagesLoaded = getBoolean(MAIN_KEY_ALL_PAGES_LOADED)
        peopleAdapter.updateList(getParcelableArrayList(MAIN_KEY_PEOPLE_LIST))
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun addPeopleToList(peopleList: List<People>) {
        peopleAdapter.addPeople(peopleList)
    }
}