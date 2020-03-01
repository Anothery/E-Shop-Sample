package com.example.eshopsample.ui.base

abstract class BasePresenter<V> : MVPPresenter<V> {
    protected var view: V? = null

    abstract fun disposeSubscriptions()

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }
}