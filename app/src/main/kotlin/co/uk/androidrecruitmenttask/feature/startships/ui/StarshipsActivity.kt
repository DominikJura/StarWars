package co.uk.androidrecruitmenttask.feature.startships.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.ContentLoadingProgressBar
import android.support.v7.widget.RecyclerView
import android.view.View.VISIBLE
import android.widget.TextView
import butterknife.BindView
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.common.ui.BaseActivity
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.View
import co.uk.androidrecruitmenttask.feature.startships.ui.adapters.StarshipsAdapter
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.KEY_STARSHIPS_LIST
import javax.inject.Inject

class StarshipsActivity : BaseActivity<Presenter>(), View {

    @BindView(R.id.starships_recyclerView)
    lateinit var starshipsRecyclerView: RecyclerView

    @BindView(android.R.id.content)
    lateinit var rootView: android.view.View

    @BindView(R.id.starship_progressBar)
    lateinit var progressBar: ContentLoadingProgressBar

    @BindView(R.id.starship_no_ship_text)
    lateinit var noStarshipsTextView: TextView

    @Inject
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var starshipsAdapter: StarshipsAdapter

    override val layoutId: Int = R.layout.activity_starships

    override fun init(savedInstanceState: Bundle?) {
        initRecycler()
        savedInstanceState?.let { restoreState(savedInstanceState) } ?: getExtras()
    }

    private fun initRecycler() = with(starshipsRecyclerView) {
        layoutManager = recyclerLayoutManager
        adapter = starshipsAdapter
    }

    private fun restoreState(savedInstanceState: Bundle) = with(savedInstanceState) {
        starshipsAdapter.updateList(getParcelableArrayList(StringConstanst.STARSHIPS_KEY_STARSHIPS_LIST))
    }

    private fun getExtras() {
        intent.getStringArrayListExtra(KEY_STARSHIPS_LIST)?.let { presenter.onStarshipsUrlExtras(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) = with(outState) {
        putParcelableArrayList(StringConstanst.STARSHIPS_KEY_STARSHIPS_LIST, starshipsAdapter.starshipsList)
        super.onSaveInstanceState(outState)
    }

    override fun addStarshipToList(starship: Starships) {
        starshipsAdapter.addStarship(starship)
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showNoStarshipsText() {
        noStarshipsTextView.visibility = VISIBLE
    }

    override fun showProgress() {
        progressBar.visibility = VISIBLE
        progressBar.show()
    }

    override fun hideProgress() {
        progressBar.hide()
    }
}
