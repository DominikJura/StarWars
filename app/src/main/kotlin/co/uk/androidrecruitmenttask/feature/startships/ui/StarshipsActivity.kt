package co.uk.androidrecruitmenttask.feature.startships.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.common.ui.BaseActivity
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract.View
import co.uk.androidrecruitmenttask.feature.startships.ui.adapters.StarshipsAdapter
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.KEY_STARSHIPS_LIST
import javax.inject.Inject

class StarshipsActivity : BaseActivity<Presenter>(), View {

    @BindView(R.id.starshipsList)
    lateinit var starshipsRecyclerView: RecyclerView

    @BindView(android.R.id.content)
    lateinit var rootView: android.view.View

    @Inject
    lateinit var recyclerLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var starshipsAdapter: StarshipsAdapter

    override val layoutId: Int = R.layout.activity_starships

    override fun init(savedInstanceState: Bundle?) {
        initRecycler()
        getExtras()
    }

    private fun getExtras() {
        intent.getStringArrayListExtra(KEY_STARSHIPS_LIST)?.let { presenter.onStarshipsUrlExtras(it) }
    }

    private fun initRecycler() = with(starshipsRecyclerView) {
        layoutManager = recyclerLayoutManager
        adapter = starshipsAdapter
    }

    override fun addStarshipsToList(starshipsList: List<Starships>) {
        starshipsAdapter.addStarships(starshipsList)
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }
}
