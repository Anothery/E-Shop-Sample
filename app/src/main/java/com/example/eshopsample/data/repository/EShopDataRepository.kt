package com.example.eshopsample.data.repository

import com.example.eshopsample.data.model.mapper.category.CategoriesResponseToDataMapper
import com.example.eshopsample.data.model.mapper.category.CategoryDataToDomainMapper
import com.example.eshopsample.data.model.mapper.product.ProductDetailDataToDomainMapper
import com.example.eshopsample.data.model.mapper.product.ProductDetailsResponseToDataMapper
import com.example.eshopsample.data.source.network.OpenCartApiService
import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.ProductDetail
import com.example.eshopsample.domain.repository.EShopRepository
import io.reactivex.Single
import javax.inject.Inject

class EShopDataRepository @Inject constructor(
    private val eShopApi: OpenCartApiService,
    private val categoriesResponseToDataMapper: CategoriesResponseToDataMapper,
    private val categoryDataToDomainMapper: CategoryDataToDomainMapper,
    private val productDetailsResponseToDataMapper: ProductDetailsResponseToDataMapper,
    private val productDetailDataToDomainMapper: ProductDetailDataToDomainMapper
) : EShopRepository {
    override fun getCategoriesList(): Single<List<Category>> {
        return eShopApi.getCategories().map {
            categoryDataToDomainMapper.map(categoriesResponseToDataMapper.map(it))
        }
    }

    override fun getProductsByCategoryId(id: Int): Single<List<ProductDetail>> {
        return eShopApi.getProductsByCategoryId(id)
            .map { productDetailDataToDomainMapper.map(it.data) }
    }

    override fun getProductDetails(id: Int): Single<ProductDetail> {
        return eShopApi.getProductDetails(id).map {
            productDetailDataToDomainMapper.map(productDetailsResponseToDataMapper.map(it))
        }
    }

}