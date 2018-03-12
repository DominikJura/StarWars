package co.uk.androidrecruitmenttask.splash.robot

import co.uk.androidrecruitmenttask.BaseRobot
import co.uk.androidrecruitmenttask.R

fun splash(func: SplashRobot.() -> Unit) = SplashRobot().apply(func)

class SplashRobot : BaseRobot() {

    init {
        isDisplayed(R.id.splash_star_wars_image)
        isDisplayed(R.id.splash_root)
    }
}