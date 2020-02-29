package com.example.eshopsample.data.model.mapper.product

import com.example.eshopsample.data.model.ProductDetailEntity
import com.example.eshopsample.data.model.ProductDetailResponse
import com.example.eshopsample.data.model.mapper.base.BaseMapper

class ProductDetailsResponseToDataMapper :
    BaseMapper<ProductDetailResponse, ProductDetailEntity>() {
    override fun map(source: ProductDetailResponse): ProductDetailEntity {
        return source.data
    }
}