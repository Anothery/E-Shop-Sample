package com.example.eshopsample.data.source.network

import com.example.eshopsample.data.model.CategoriesResponse
import com.example.eshopsample.data.model.ProductDetailResponse
import com.example.eshopsample.data.model.ProductsByCategoryResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenCartApiService {
    @GET("categories")
    fun getCategories(): Single<CategoriesResponse>

    @GET("products/category/{id}")
    fun getProductsByCategoryId(@Path("id") id: Int): Single<ProductsByCategoryResponse>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id: Int): Single<ProductDetailResponse>
}