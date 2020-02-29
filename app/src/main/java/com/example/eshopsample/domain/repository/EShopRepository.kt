package com.example.eshopsample.domain.repository

import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.Product
import com.example.eshopsample.domain.model.ProductDetail
import io.reactivex.Single

interface EShopRepository {
    fun getCategoriesList(): Single<List<Category>>
    fun getProductsByCategoryId(id: Int): Single<List<ProductDetail>>
    fun getProductDetails(id: Int): Single<ProductDetail>
}