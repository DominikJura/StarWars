package co.uk.androidrecruitmenttask.feature.splash.navigation

import android.content.Intent
import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class SplashActivityRouterTest : BaseTest() {

    @Mock
    private lateinit var activity: SplashActivity

    private lateinit var router: SplashActivityContract.Router

    override fun setup() {
        super.setup()
        router = SplashActivityRouter(activity)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(activity)
    }

    @Test
    fun `should navigate to main activity after navigateToMain is called`() {
        val expectedActivity = MainActivity::class
        val flags = listOf(Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_CLEAR_TASK)

        router.navigateToMain()

        verify(activity, times(1)).startActivity(expectedActivity, flags)
    }
}