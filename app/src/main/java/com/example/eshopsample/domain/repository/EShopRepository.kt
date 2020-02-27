package com.example.eshopsample.domain.repository

import com.example.eshopsample.domain.model.Category
import io.reactivex.Single

interface EShopRepository {
    fun getCategoriesList(): Single<List<Category>>
}