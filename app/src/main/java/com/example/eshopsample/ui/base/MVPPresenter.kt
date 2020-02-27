package com.example.eshopsample.ui.base

interface MVPPresenter<V> {
    fun onAttach(view: V)
    fun onDetach()
}