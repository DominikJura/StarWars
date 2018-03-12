package co.uk.androidrecruitmenttask.feature.splash

import co.uk.androidrecruitmenttask.feature.common.BaseContract

interface SplashActivityContract {

    interface View

    interface Router {
        fun navigateToMain()
    }

    interface Presenter : BaseContract.Presenter
}