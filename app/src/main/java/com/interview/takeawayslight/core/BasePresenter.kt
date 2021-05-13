package com.interview.takeawayslight.core

import java.lang.ref.WeakReference

abstract class PresenterViewRefHolder<V : BaseView> : BasePresenter<V> {
    override fun attachView(view: V) {
        viewRef = WeakReference(view)
    }

    override fun detachView() {
        val ref = viewRef
        if (ref != null) {
            ref.clear()
            viewRef = null
        }
    }

    private var viewRef: WeakReference<V>? = null

    private fun view(): V? {
        return viewRef?.get()
    }

    internal fun <R> ifViewAttachedThen(block: V.() -> R): R? {
        return view()?.let(block)
    }
}

interface BasePresenter<V : BaseView> {
    fun attachView(view: V)
    fun detachView()
}

interface BaseView