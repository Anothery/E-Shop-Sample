package com.example.eshopsample.ui.main

import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.CategoryWithProducts
import com.example.eshopsample.ui.base.MVPPresenter

interface MainContract {
    interface View {

        fun updateCategories()
    }

    interface Presenter : MVPPresenter<View> {
        fun initialize(categories: ArrayList<CategoryWithProducts>)
        fun updateCategories()
    }
}