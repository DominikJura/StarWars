package co.uk.androidrecruitmenttask

import android.app.Activity
import android.app.Application
import co.uk.androidrecruitmenttask.util.injection.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class StarWarsApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> =
            activityInjector

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initAppComponent()
    }

    private fun initAppComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}
