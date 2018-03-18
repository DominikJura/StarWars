package co.uk.androidrecruitmenttask.main

import android.support.test.rule.ActivityTestRule
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import co.uk.androidrecruitmenttask.main.robot.main
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    companion object {
        const val MOCK_PEOPLE_LIST_SIZE = 100
        const val PEOPLE_LIST_LAST_INDEX = MOCK_PEOPLE_LIST_SIZE - 1
    }

    @get:Rule
    private val activityTestRule = ActivityTestRule<MainActivity>(
            MainActivity::class.java,
            false,
            false
    )

    @Before
    fun setup() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun checkMain() {
        main { }
    }

    @Test
    fun scrollAndCheckAllItems() {
        main {
            scrollFromFirstToLastStarWarsPerson(PEOPLE_LIST_LAST_INDEX)
        }
    }

    @Test
    fun scrollToLastItemThenClickItAndBack() {
        main {
            scrollFromFirstToLastStarWarsPerson(PEOPLE_LIST_LAST_INDEX)
            clickItem(PEOPLE_LIST_LAST_INDEX) {
                back()
            }
        }
    }
}