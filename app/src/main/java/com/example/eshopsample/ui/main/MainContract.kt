package com.example.eshopsample.ui.main

import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.domain.model.Product
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.ui.base.MVPPresenter

interface MainContract {
    interface View {

        fun updateCategories()
        fun openDetails(id: Int)
        fun showProgressBar()
        fun hideProgressBar()
        fun showRecyclerView()
        fun hideRecyclerView()
    }

    interface Presenter : MVPPresenter<View> {
        fun initialize(categories: ArrayList<CategoryWithProducts>)
        fun updateCategories()
        fun onProductClicked(productDetails: ProductDetail)
    }
}