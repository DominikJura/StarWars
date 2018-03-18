package co.uk.androidrecruitmenttask.feature.main.navigation

import android.content.Intent
import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.feature.main.MainActivityContract
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class MainActivityRouterTest : BaseTest() {

    @Mock
    private lateinit var activity: MainActivity

    @Mock
    private lateinit var starshipsUrlList: ArrayList<String>

    private lateinit var router: MainActivityContract.Router

    override fun setup() {
        super.setup()
        router = MainActivityRouter(activity)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(activity)
    }

    @Test
    fun `should navigate to starships activity after navigateToStarshipScreen is called`() {
        router.navigateToStarshipScreen(starshipsUrlList)

        verify(activity, times(1)).startActivity(any(Intent::class.java))
    }
}