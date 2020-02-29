package com.example.eshopsample.data.model

data class CategoriesResponse(
    val success: Int,
    val error: List<String>,
    val data: HashMap<String, List<CategoryEntity>>
)