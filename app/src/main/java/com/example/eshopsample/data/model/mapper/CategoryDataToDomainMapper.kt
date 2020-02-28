package com.example.eshopsample.data.model.mapper

import com.example.eshopsample.data.model.CategoryEntity
import com.example.eshopsample.domain.model.Category

class CategoryDataToDomainMapper : Mapper<CategoryEntity, Category>() {
    override fun map(source: CategoryEntity): Category {
        return Category(
            source.category_id,
            source.name,
            source.description,
            source.image,
            source.original_image
        )
    }
}