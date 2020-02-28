package com.example.eshopsample.ui.main

import android.util.Log
import com.example.eshopsample.domain.model.CategoryWithProducts
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
            DisposableSubscriber<CategoryWithProducts>() {
            override fun onComplete() {}

            override fun onNext(item: CategoryWithProducts) {
                categories.add(item)
                view?.updateCategories()
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