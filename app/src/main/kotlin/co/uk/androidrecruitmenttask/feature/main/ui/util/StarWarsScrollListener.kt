package co.uk.androidrecruitmenttask.feature.main.ui.util

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class StarWarsScrollListener() : RecyclerView.OnScrollListener() {

    companion object {
        private const val VISIBLE_THRESHOLD = 5
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!isLoading && isEndOfList(recyclerView.layoutManager as LinearLayoutManager)) {
            onLoadMore()
        }
    }

    private fun isEndOfList(layoutManager: LinearLayoutManager) = with(layoutManager) {
        return@with (itemCount - childCount) <= (findFirstVisibleItemPosition() + VISIBLE_THRESHOLD)
    }

    abstract var isLoading: Boolean
    abstract fun onLoadMore()
}