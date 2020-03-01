package com.example.eshopsample.ui.detail

import android.util.Log
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.domain.usecase.UseCaseGetProductDetails
import com.example.eshopsample.ui.base.BasePresenter
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val useCaseGetProductDetails: UseCaseGetProductDetails) :
    DetailContract.Presenter, BasePresenter<DetailContract.View>() {

    private lateinit var relatedProducts: ArrayList<ProductDetail>

    override fun initialize(productId: Int, relatedProducts: ArrayList<ProductDetail>) {
        this.relatedProducts = relatedProducts
        getProductDetails(productId)
    }

    private fun getProductDetails(productId: Int) {
        useCaseGetProductDetails.setProductId(productId)
        useCaseGetProductDetails.subscribe(object : DisposableSubscriber<ProductDetail>() {
            override fun onComplete() {}
            override fun onNext(t: ProductDetail) = onProductDetailsArrived(t)
            override fun onError(t: Throwable) = onProductDetailsError(t)
        })
    }

    private fun onProductDetailsArrived(productDetails: ProductDetail) {
        view?.loadData(productDetails)
        if (productDetails.product_relateds.isNotEmpty()) {
            view?.showRelatedProducts()
            getRelatedProducts(productDetails.product_relateds)
        }
    }

    private fun onProductDetailsError(throwable: Throwable) {
        throwable.message?.let {
            Log.e(this::class.java.simpleName, it)
        }
        view?.showErrorScreen()
    }


    private fun getRelatedProducts(relatedProducts: List<String>) {
        for (id in relatedProducts) {
            val productId = id.toIntOrNull()
            productId?.let {
                useCaseGetProductDetails.setProductId(it)
                useCaseGetProductDetails.subscribe(object : DisposableSubscriber<ProductDetail>() {
                    override fun onComplete() {}
                    override fun onNext(t: ProductDetail) = onRelatedDeviceArrived(t)
                    override fun onError(t: Throwable) = onRelatedDeviceError(t)
                })
            }
        }
    }

    private fun onRelatedDeviceArrived(productDetails: ProductDetail) {
        relatedProducts.add(productDetails)
        view?.updateRelatedList()
    }

    private fun onRelatedDeviceError(throwable: Throwable) {
        throwable.message?.let {
            Log.e(this@DetailPresenter::class.java.simpleName, it)
        }
        view?.hideRelatedProducts()
    }

    override fun onRelatedProductClicked(product: ProductDetail) {
        view?.openDetails(product.id)
    }

    override fun onConnectionLost() {
        view?.showConnectionErrorScreen()
    }

    override fun onConnectionAvailable() {
        view?.hideConnectionErrorScreen()
    }

    override fun onHomePressed() {
        view?.finishActivity()
    }

    override fun disposeSubscriptions() {
        useCaseGetProductDetails.dispose()
    }

    override fun onDetach() {
        disposeSubscriptions()
        super.onDetach()
    }

}