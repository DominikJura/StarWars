package co.uk.androidrecruitmenttask.util.injection.modules

import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract
import co.uk.androidrecruitmenttask.feature.splash.navigation.SplashActivityRouter
import co.uk.androidrecruitmenttask.feature.splash.presentation.SplashActivityPresenter
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashActivityModule {

    @Provides
    fun router(activity: SplashActivity): SplashActivityContract.Router =
            SplashActivityRouter(activity)

    @RuntimeScope
    @Provides
    fun presenter(
            router: SplashActivityContract.Router,
            compositeDisposable: CompositeDisposable
    ): SplashActivityContract.Presenter =
            SplashActivityPresenter(router, compositeDisposable)
}