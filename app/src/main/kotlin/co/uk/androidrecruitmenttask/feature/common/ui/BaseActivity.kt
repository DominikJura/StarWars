package co.uk.androidrecruitmenttask.feature.common.ui

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import co.uk.androidrecruitmenttask.feature.common.BaseContract
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity<P : BaseContract.Presenter>
    : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject lateinit var presenter: P

    abstract val layoutId: Int

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        ButterKnife.bind(this)
        presenter.initialize()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
            fragmentInjector
}
