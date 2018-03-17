package co.uk.androidrecruitmenttask.feature.main.navigation

import android.content.Intent
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract.Router
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import co.uk.androidrecruitmenttask.feature.startships.StarshipsActivity
import co.uk.androidrecruitmenttask.util.configuration.StringConstanst.KEY_STARSHIPS_LIST

class MainActivityRouter(private val activity: MainActivity) : Router {

    override fun navigateToStarshipScreen(starshipsUrlList: ArrayList<String>) {
        val intent = Intent(activity, StarshipsActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putStringArrayListExtra(KEY_STARSHIPS_LIST, starshipsUrlList)

        activity.startActivity(intent)
    }
}