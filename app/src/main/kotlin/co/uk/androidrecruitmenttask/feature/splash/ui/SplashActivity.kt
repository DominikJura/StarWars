package co.uk.androidrecruitmenttask.feature.splash.ui

import co.uk.androidrecruitmenttask.R
import co.uk.androidrecruitmenttask.feature.common.ui.BaseActivity
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract.Presenter
import co.uk.androidrecruitmenttask.feature.splash.SplashActivityContract.View

class SplashActivity : BaseActivity<Presenter>(), View {

    override val layoutId: Int = R.layout.activity_splash
}
