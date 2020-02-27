package com.example.eshopsample.data.model.mapper

import com.example.eshopsample.data.model.CategoryEntity
import com.example.eshopsample.domain.model.Category

class CategoryDataToDomainMapper : Mapper<CategoryEntity, Category>() {
    override fun map(source: CategoryEntity): Category {
        return Category(
            source.category_id,
            source.name,
            source.description,
            source.sort_order,
            source.meta_title,
            source.meta_description,
            source.meta_meta_keywordword,
            source.language_id,
            source.status,
            source.parent_id,
            source.column,
            source.top,
            source.category_store,
            source.category_layout,
            source.category_filter,
            source.image,
            source.original_image,
            source.categories
        )
    }
}