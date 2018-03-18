package co.uk.androidrecruitmenttask.main.robot

import co.uk.androidrecruitmenttask.BaseRobot
import co.uk.androidrecruitmenttask.R

fun main(func: MainRobot.() -> Unit) = MainRobot().apply(func)

class MainRobot : BaseRobot() {

    init {
        isDisplayed(R.id.main_people_recycler)
    }
}