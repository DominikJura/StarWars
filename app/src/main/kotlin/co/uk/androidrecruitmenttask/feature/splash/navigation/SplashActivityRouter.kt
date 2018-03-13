package co.uk.androidrecruitmenttask.feature.splash.navigation

import android.content.Intent
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract.Router
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity

class SplashActivityRouter(private val activity: SplashActivity) : Router {

    override fun navigateToMain() {
        activity.startActivity(
                MainActivity::class,
                listOf(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TASK))
    }
}