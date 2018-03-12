package co.uk.androidrecruitmenttask.feature.common.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import dagger.android.support.AndroidSupportInjection
import co.uk.androidrecruitmenttask.feature.common.BaseContract
import javax.inject.Inject

abstract class BaseFragment<P : BaseContract.Presenter> : Fragment() {

    @Inject lateinit var presenter: P

    abstract val layoutId: Int

    private lateinit var unbinder: Unbinder

    @CallSuper
    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        unbinder = ButterKnife.bind(this, view)
        return view
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        presenter.initialize()
    }

    protected open fun initialize() = Unit

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter.clear()
        unbinder.unbind()
    }
}