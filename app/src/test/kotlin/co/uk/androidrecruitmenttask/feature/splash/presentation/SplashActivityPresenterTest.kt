package co.uk.androidrecruitmenttask.feature.splash.presentation

import co.uk.androidrecruitmenttask.BaseTest
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions

class SplashActivityPresenterTest : BaseTest() {

    @Mock
    lateinit var router: SplashActivityContract.Router

    @Mock
    lateinit var compositeDisposable: CompositeDisposable

    lateinit var presenter: SplashActivityContract.Presenter

    override fun setup() {
        super.setup()
        trampolineRxPlugin()

        presenter = SplashActivityPresenter(router, compositeDisposable)
    }

    override fun tearDown() {
        super.tearDown()
        verifyNoMoreInteractions(router, compositeDisposable)
    }

    @Test
    fun `should add delay disposable and navigate to main after initialize is called`() {
        presenter.initialize()

        verify(compositeDisposable, times(1)).add(any(Disposable::class.java))
        verify(router, times(1)).navigateToMain()
    }

    @Test
    fun `should clear composite disposable after clear is called`() {
        presenter.clear()

        verify(compositeDisposable, times(1)).clear()
    }
}