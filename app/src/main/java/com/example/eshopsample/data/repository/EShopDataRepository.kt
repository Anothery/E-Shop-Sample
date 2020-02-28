package com.example.eshopsample.data.repository

import com.example.eshopsample.data.model.CategoriesResponse
import com.example.eshopsample.data.model.CategoryEntity
import com.example.eshopsample.data.model.mapper.CategoryDataToDomainMapper
import com.example.eshopsample.data.model.mapper.ProductDataToDomainMapper
import com.example.eshopsample.data.source.network.OpenCartApiService
import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.Product
import com.example.eshopsample.domain.repository.EShopRepository
import io.reactivex.Single
import javax.inject.Inject

class EShopDataRepository @Inject constructor(
    private val eShopApi: OpenCartApiService,
    private val categoryDataToDomainMapper: CategoryDataToDomainMapper,
    private val productDataToDomainMapper: ProductDataToDomainMapper
) :
    EShopRepository {
    override fun getCategoriesList(): Single<List<Category>> {
        return eShopApi.getCategories().map {
            categoryDataToDomainMapper.map(getCategoryListFromCategoriesResponse(it))
        }
    }

    private fun getCategoryListFromCategoriesResponse(categoriesResponse: CategoriesResponse): List<CategoryEntity> {
        val list = ArrayList<CategoryEntity>()
        for (item in categoriesResponse.data.toList()) {
            if (item.second.isNotEmpty()) {
                list.add(item.second[0])
            }
        }
        return list
    }

    override fun getProductsByCategoryId(id: Int): Single<List<Product>> {
        return eShopApi.getProductsByCategoryId(id).map { productDataToDomainMapper.map(it.data) }
    }
}