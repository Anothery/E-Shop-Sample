package com.example.eshopsample.data.model.mapper

import com.example.eshopsample.data.model.ProductEntity
import com.example.eshopsample.domain.model.Product

class ProductDataToDomainMapper : Mapper<ProductEntity, Product>() {
    override fun map(source: ProductEntity): Product {
        return Product(
            source.id,
            source.manufacturer,
            source.image,
            source.images,
            source.original_image,
            source.original_images,
            source.product_description[0].name
        )
    }
}