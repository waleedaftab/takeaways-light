package com.interview.takeawayslight.core

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : DaggerAppCompatActivity() {

    protected abstract val presenter: P

    @Suppress("UNCHECKED_CAST")
    override fun onStart() {
        super.onStart()
        (this as? V)?.let { presenter.attachView(it) }
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}