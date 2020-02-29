package com.example.eshopsample.ui.main

import android.util.Log
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.usecase.UseCaseGetCategories
import com.example.eshopsample.domain.usecase.UseCaseGetCategoryWithProducts
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val useCaseGetCategoryWithProducts: UseCaseGetCategoryWithProducts
) : MainContract.Presenter {
    private var view: MainContract.View? = null
    private lateinit var categories: ArrayList<CategoryWithProducts>

    override fun initialize(categories: ArrayList<CategoryWithProducts>) {
        this.categories = categories
        updateCategories()
    }

    override fun updateCategories() {
        useCaseGetCategoryWithProducts.subscribe(object :
            DisposableSubscriber<List<CategoryWithProducts>>() {
            override fun onComplete() {
                view?.updateCategories()
            }

            override fun onNext(list: List<CategoryWithProducts>) {
                categories.addAll(list)
            }

            override fun onError(t: Throwable?) {
                t?.let {
                    it.message?.let { message ->
                        Log.e(this::class.java.simpleName, message)
                    }
                }

            }

        })
    }

    override fun onAttach(view: MainContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        useCaseGetCategoryWithProducts.dispose()
    }
}