package com.example.eshopsample.data.model

//Shortened category DTO
data class CategoryEntity(
    val category_id: Int,
    val name: String,
    val description: String,
    val image: String,
    val original_image: String
)
