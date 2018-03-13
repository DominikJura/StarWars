package co.uk.androidrecruitmenttask.util.injection.binding

import android.support.v7.app.AppCompatActivity
import co.uk.androidrecruitmenttask.feature.splash.ui.SplashActivity
import co.uk.androidrecruitmenttask.feature.main.ui.MainActivity
import co.uk.androidrecruitmenttask.util.injection.RuntimeScope
import co.uk.androidrecruitmenttask.util.injection.modules.ApiModule
import co.uk.androidrecruitmenttask.util.injection.modules.SplashActivityModule
import co.uk.androidrecruitmenttask.util.injection.modules.MainActivityModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinderModule {

    @Binds
    abstract fun activity(activity: AppCompatActivity): AppCompatActivity

    @RuntimeScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @RuntimeScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}
