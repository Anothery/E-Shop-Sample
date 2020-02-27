package com.example.eshopsample.data.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    val success: Int,
    val error: List<String>,
    val data: HashMap<String, List<CategoryEntity>>
)

data class CategoryEntity(

    val category_id: Int,
    val name: String,
    val description: String,
    val sort_order: Int,
    val meta_title: String,
    val meta_description: String,
    val meta_meta_keywordword: String?,
    val language_id: Int,
    val status: Int,
    val parent_id: Int,
    val column: Int,
    val top: Int,
    val category_store: List<Int>,
    val category_seo_url: List<Category_seo_url>,
    val category_layout: List<String>,
    val category_filter: List<String>,
    val image: String,
    val original_image: String,
    val categories: List<String>
)

data class Category_seo_url(
    @SerializedName("1")
    val url: String
)
