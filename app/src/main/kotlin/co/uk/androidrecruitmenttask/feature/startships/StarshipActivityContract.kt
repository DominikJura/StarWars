package co.uk.androidrecruitmenttask.feature.startships

import co.uk.androidrecruitmenttask.data.api.Starships
import co.uk.androidrecruitmenttask.feature.common.BaseContract

interface StarshipActivityContract {

    interface View {
        fun addStarshipsToList(starshipsList: List<Starships>)
        fun showSnackBar(message: String)
    }

    interface Router

    interface Presenter : BaseContract.Presenter {
        fun onStarshipsUrlExtras(starshipsUrlList: ArrayList<String>)
    }
}