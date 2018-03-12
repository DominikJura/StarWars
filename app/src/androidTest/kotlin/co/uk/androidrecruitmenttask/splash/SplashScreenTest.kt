package co.uk.androidrecruitmenttask.splash

import android.support.test.rule.ActivityTestRule
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.splash.robot.splash
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    private val activityTestRule = ActivityTestRule<SplashActivity>(
            SplashActivity::class.java,
            false,
            false
    )

    @Before
    fun setup() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun checkSplash() {
        splash { }
    }
}