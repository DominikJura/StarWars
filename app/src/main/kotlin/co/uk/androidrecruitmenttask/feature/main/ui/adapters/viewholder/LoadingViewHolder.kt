package co.uk.androidrecruitmenttask.feature.main.ui.adapters.viewholder

import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import co.uk.androidrecruitmenttask.R

class LoadingViewHolder(loadingView: View) : RecyclerView.ViewHolder(loadingView) {

    @BindView(R.id.item_loading_progressBar)
    lateinit var progressBar: ContentLoadingProgressBar

    init {
        ButterKnife.bind(this, itemView)
    }

    fun showProgress() {
        progressBar.show()
    }
}