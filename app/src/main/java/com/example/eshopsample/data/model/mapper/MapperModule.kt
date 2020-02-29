package com.example.eshopsample.data.model.mapper

import com.example.eshopsample.data.model.mapper.category.CategoriesResponseToDataMapper
import com.example.eshopsample.data.model.mapper.category.CategoryDataToDomainMapper
import com.example.eshopsample.data.model.mapper.product.ProductDetailDataToDomainMapper
import com.example.eshopsample.data.model.mapper.product.ProductDetailsResponseToDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Singleton
    @Provides
    fun provideCategoryDataToDomainMapper() = CategoryDataToDomainMapper()

    @Singleton
    @Provides
    fun provideCategoriesResponseToDataMapper() = CategoriesResponseToDataMapper()

    @Singleton
    @Provides
    fun provideProductDetailsResponseToDataMapper() = ProductDetailsResponseToDataMapper()

    @Singleton
    @Provides
    fun provideProductDetailDataToDomainMapper(categoryMapper: CategoryDataToDomainMapper) =
        ProductDetailDataToDomainMapper(categoryMapper)

}

