package com.example.eshopsample.ui.main

import android.util.Log
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.domain.usecase.UseCaseGetCategoryWithProducts
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val useCaseGetCategoryWithProducts: UseCaseGetCategoryWithProducts
) : MainContract.Presenter {
    private var view: MainContract.View? = null
    private lateinit var categories: ArrayList<CategoryWithProducts>

    override fun initialize(categories: ArrayList<CategoryWithProducts>) {
        view?.hideRecyclerView()
        view?.showProgressBar()
        this.categories = categories
        updateCategories()
    }

    override fun updateCategories() {
        useCaseGetCategoryWithProducts.subscribe(object :
            DisposableSubscriber<List<CategoryWithProducts>>() {
            override fun onComplete() {}
            override fun onNext(list: List<CategoryWithProducts>) = onCategoriesListArrived(list)
            override fun onError(t: Throwable) = onCategoriesListError(t)
        })
    }

    private fun onCategoriesListArrived(list: List<CategoryWithProducts>) {
        view?.hideProgressBar()
        view?.showRecyclerView()
        categories.addAll(list)
        view?.updateCategories()
    }

    private fun onCategoriesListError(throwable: Throwable) {
        throwable.message?.let {
            Log.e(this::class.java.simpleName, it)
        }
    }

    override fun onProductClicked(productDetails: ProductDetail) {
        view?.openDetails(productDetails.id)
    }

    override fun onAttach(view: MainContract.View) {
        this.view = view
    }

    override fun onDetach() {
        view = null
        useCaseGetCategoryWithProducts.dispose()
    }
}