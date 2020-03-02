package com.example.eshopsample.ui.main

import android.util.Log
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.domain.usecase.UseCaseGetCategoryWithProducts
import com.example.eshopsample.ui.base.BasePresenter
import io.reactivex.subscribers.DisposableSubscriber
import java.net.UnknownHostException
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val useCaseGetCategoryWithProducts: UseCaseGetCategoryWithProducts
) : MainContract.Presenter, BasePresenter<MainContract.View>() {

    private lateinit var categories: ArrayList<CategoryWithProducts>

    override fun initialize(categories: ArrayList<CategoryWithProducts>) {
        view?.hideRecyclerView()
        view?.showProgressBar()
        this.categories = categories
        updateCategories()
    }

    private fun updateCategories() {
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
        categories.clear()
        categories.addAll(list)
        view?.updateCategories()
    }

    private fun onCategoriesListError(throwable: Throwable) {
        if (throwable is UnknownHostException) {
            view?.showConnectionErrorScreen()
        }
        throwable.message?.let {
            Log.e(this::class.java.simpleName, it)
        }
    }

    override fun onConnectionLost() {
        view?.showConnectionErrorScreen()
    }

    override fun onConnectionAvailable() {
        view?.hideConnectionErrorScreen()
        updateCategories()
    }

    override fun onProductClicked(productDetails: ProductDetail) {
        view?.openDetails(productDetails.id)
    }

    override fun disposeSubscriptions() {
        useCaseGetCategoryWithProducts.dispose()
    }

    override fun onDetach() {
        disposeSubscriptions()
        super.onDetach()
    }
}