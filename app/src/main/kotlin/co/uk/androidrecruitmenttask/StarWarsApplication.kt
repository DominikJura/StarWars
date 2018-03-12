package co.uk.androidrecruitmenttask

import android.app.Activity
import android.app.Application
import co.uk.androidrecruitmenttask.data.api.ApiComponent
import co.uk.androidrecruitmenttask.data.api.ApiModule
import co.uk.androidrecruitmenttask.data.api.DaggerApiComponent
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

    var apiComponent: ApiComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initAppComponent()

        // TODO change to new dagger implementation
        apiComponent = DaggerApiComponent.builder()
                .apiModule(ApiModule())
                .build()
    }

    private fun initAppComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}
