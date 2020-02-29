package com.example.eshopsample.data.model.mapper.category

import com.example.eshopsample.data.model.CategoryEntity
import com.example.eshopsample.data.model.mapper.base.BaseMapper
import com.example.eshopsample.domain.model.Category

class CategoryDataToDomainMapper : BaseMapper<CategoryEntity, Category>() {
    override fun map(source: CategoryEntity): Category {
        return Category(
            source.category_id,
            source.name,
            source.description
        )
    }
}