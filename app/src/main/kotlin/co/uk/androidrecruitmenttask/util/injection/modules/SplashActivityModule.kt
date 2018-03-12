package co.uk.androidrecruitmenttask.util.injection.modules

import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract
import co.uk.androidrecruitmenttask.feature.splash.presentation.SplashActivityPresenter
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.util.injection.RuntimeScope
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun view(activity: SplashActivity): SplashActivityContract.View = activity

    @RuntimeScope
    @Provides
    fun presenter(view: SplashActivityContract.View): SplashActivityContract.Presenter =
            SplashActivityPresenter()
}