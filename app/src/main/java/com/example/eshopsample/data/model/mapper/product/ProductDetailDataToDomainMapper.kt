package com.example.eshopsample.data.model.mapper.product

import com.example.eshopsample.data.model.ProductDetailEntity
import com.example.eshopsample.data.model.mapper.base.BaseMapper
import com.example.eshopsample.data.model.mapper.category.CategoryDataToDomainMapper
import com.example.eshopsample.domain.model.Category
import com.example.eshopsample.domain.model.ProductDetail
import javax.inject.Inject

class ProductDetailDataToDomainMapper @Inject constructor(private val categoryDataToDomainMapper: CategoryDataToDomainMapper) :
    BaseMapper<ProductDetailEntity, ProductDetail>() {
    override fun map(source: ProductDetailEntity): ProductDetail {
        val categoriesList = ArrayList<Category>()
        for (category in source.category) {
            if (category.isNotEmpty()) categoriesList.add(categoryDataToDomainMapper.map(category[0]))
        }

        return ProductDetail(
            category = categoriesList,
            id = source.id,
            manufacturer = source.manufacturer,
            price_formated = source.price_formated,
            model = source.model,
            image = source.image,
            original_image = source.original_image,
            length = source.length,
            weight = source.weight,
            length_class = source.length_class,
            weight_class = source.weight_class,
            width = source.width,
            height = source.height,
            productName = source.product_description[0].name,
            productDescription = source.product_description[0].description,
            product_relateds = source.product_relateds
        )
    }
}