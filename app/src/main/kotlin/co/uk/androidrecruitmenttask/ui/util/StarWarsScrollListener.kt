package co.uk.androidrecruitmenttask.ui.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class StarWarsScrollListener : RecyclerView.OnScrollListener() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    var tmp = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

//        if(tmp >= (recyclerView.layoutManager as LinearLayoutManager).itemCount) {
//            return
//        }

        if (!isLastPage && !isLoading && isEndOfList(recyclerView.layoutManager as LinearLayoutManager)) {
//            tmp = (recyclerView.layoutManager as LinearLayoutManager).itemCount
            onLoadMore()
        }
    }

    private fun isEndOfList(layoutManager: LinearLayoutManager) = with(layoutManager) {
        return@with findLastVisibleItemPosition() + VISIBLE_THRESHOLD > itemCount
    }

    abstract var isLoading: Boolean
    abstract var isLastPage: Boolean
    abstract fun onLoadMore()
}