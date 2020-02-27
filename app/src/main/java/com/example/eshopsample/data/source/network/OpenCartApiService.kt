package com.example.eshopsample.data.source.network

import com.example.eshopsample.data.model.CategoriesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface OpenCartApiService {
    @GET("categories")
    fun getCategories(): Single<CategoriesResponse>
}