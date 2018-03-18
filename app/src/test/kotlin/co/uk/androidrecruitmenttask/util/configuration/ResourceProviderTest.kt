package co.uk.androidrecruitmenttask.util.configuration

import android.content.Context
import co.uk.androidrecruitmenttask.BaseTest
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock

class ResourceProviderTest : BaseTest() {

    @Mock
    private lateinit var context: Context

    private lateinit var resourceProvider: ResourceProvider

    override fun setup() {
        super.setup()

        resourceProvider = ResourceProviderImpl(context)
    }

    @Ignore
    @Test
    fun getString() {
        //TODO write test
    }
}