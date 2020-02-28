package com.example.eshopsample.domain.repository

import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.Product
import io.reactivex.Single

interface EShopRepository {
    fun getCategoriesList(): Single<List<Category>>
    fun getProductsByCategoryId(id: Int): Single<List<Product>>
}