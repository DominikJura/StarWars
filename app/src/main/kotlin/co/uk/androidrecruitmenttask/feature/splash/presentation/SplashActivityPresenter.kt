package co.uk.androidrecruitmenttask.feature.splash.presentation

import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract.Router
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class SplashActivityPresenter(
        private val router: Router,
        private val compositeDisposable: CompositeDisposable
) : Presenter {

    companion object {
        private const val SPLASH_SCREEN_DELAY_IN_SECONDS = 2L
    }

    override fun initialize() {
        compositeDisposable.add(
                getDelayObservable()
                        .subscribe { router.navigateToMain() }
        )
    }

    private fun getDelayObservable(): Observable<Unit> =
            Observable.just(Unit).delay(SPLASH_SCREEN_DELAY_IN_SECONDS, TimeUnit.SECONDS)

    override fun clear() {
        compositeDisposable.clear()
    }
}