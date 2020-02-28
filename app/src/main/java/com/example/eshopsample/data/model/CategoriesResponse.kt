package com.example.eshopsample.data.model

data class CategoriesResponse(
    val success: Int,
    val error: List<String>,
    val data: HashMap<String, List<CategoryEntity>>
)

// Short category DTO
data class CategoryEntity(
    val category_id: Int,
    val name: String,
    val description: String,
    val image: String,
    val original_image: String
)
