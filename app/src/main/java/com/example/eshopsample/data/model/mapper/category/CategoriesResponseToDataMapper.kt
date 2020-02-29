package com.example.eshopsample.data.model.mapper.category

import com.example.eshopsample.data.model.CategoriesResponse
import com.example.eshopsample.data.model.CategoryEntity
import com.example.eshopsample.data.model.mapper.base.BaseMapper

class CategoriesResponseToDataMapper :
    BaseMapper<CategoriesResponse, List<CategoryEntity>>() {
    override fun map(source: CategoriesResponse): List<CategoryEntity> {
        val list = ArrayList<CategoryEntity>()
        for (item in source.data.toSortedMap().toList()) {
            if (item.second.isNotEmpty()) {
                list.add(item.second[0])
            }
        }
        return list
    }
}