package com.example.eshopsample.ui.main

import com.example.eshopsample.domain.usecase.UseCaseGetCategories
import javax.inject.Inject

class MainPresenter @Inject constructor(private val useCaseGetCategories: UseCaseGetCategories) :
    MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun onAttach(view: MainContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
    }
}