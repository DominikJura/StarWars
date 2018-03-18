package co.uk.androidrecruitmenttask.starships.robot

import co.uk.androidrecruitmenttask.BaseRobot
import co.uk.androidrecruitmenttask.R

fun main(func: StarshipsRobot.() -> Unit) = StarshipsRobot().apply(func)

class StarshipsRobot : BaseRobot() {

    init {
        isDisplayed(R.id.starships_recyclerView)
    }
}