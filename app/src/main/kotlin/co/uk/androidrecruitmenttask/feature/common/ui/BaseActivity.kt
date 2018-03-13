package co.uk.androidrecruitmenttask.feature.common.ui

import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import co.uk.androidrecruitmenttask.feature.common.BaseContract
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<P : BaseContract.Presenter>
    : AppCompatActivity() {

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

    fun startActivity(activityClass: KClass<*>, flags: List<Int>? = null) {
        val intent = Intent(this, activityClass.java)
        flags?.forEach { intent.addFlags(it) }
        startActivity(intent)
    }
}
