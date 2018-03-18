package co.uk.androidrecruitmenttask.feature.startships.presentation

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.feature.startships.StarshipActivityContract
import co.uk.androidrecruitmenttask.util.repository.Repository
import co.uk.androidrecruitmenttask.util.tools.HttpErrorProvider
import io.reactivex.disposables.CompositeDisposable
import org.junit.Ignore
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verifyNoMoreInteractions

class StarshipActivityPresenterTest : BaseTest() {

    @Mock
    private lateinit var view: StarshipActivityContract.View

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var httpErrorProvider: HttpErrorProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var presenter: StarshipActivityContract.Presenter

    override fun setup() {
        super.setup()

        presenter = StarshipActivityPresenter(
                view,
                repository,
                httpErrorProvider,
                compositeDisposable
        )
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(view,
                repository,
                httpErrorProvider,
                compositeDisposable)
    }

    @Ignore
    @Test
    fun initialize() {
        //TODO write test
    }

    @Ignore
    @Test
    fun onStarshipsUrlExtras() {
        //TODO write test
    }

    @Ignore
    @Test
    fun clear() {
        //TODO write test
    }
}