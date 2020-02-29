package com.example.eshopsample.ui.detail

import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.ui.base.MVPPresenter

interface DetailContract {
    interface View {
        fun loadData(productDetails: ProductDetail)
        fun finishActivity()
        fun showErrorScreen()
        fun updateRelatedList()
        fun hideRelatedProducts()
        fun openDetails(id: Int)
        fun showRelatedProducts()
    }

    interface Presenter : MVPPresenter<View> {

        fun initialize(productId: Int, relatedProducts: ArrayList<ProductDetail>)
        fun onHomePressed()
        fun onRelatedProductClicked(product: ProductDetail)
    }
}