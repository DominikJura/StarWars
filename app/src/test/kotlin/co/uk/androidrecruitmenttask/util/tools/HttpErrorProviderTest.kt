package co.uk.androidrecruitmenttask.util.tools

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.util.configuration.ResourceProvider
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock

class HttpErrorProviderTest : BaseTest() {

    @Mock
    private lateinit var resourceProvider: ResourceProvider

    private lateinit var httpErrorProvider: HttpErrorProvider

    override fun setup() {
        super.setup()

        httpErrorProvider = HttpErrorProviderImpl(resourceProvider)
    }

    @Ignore
    @Test
    fun getStartWarsPeopleMessage() {
        //TODO write test
    }
}