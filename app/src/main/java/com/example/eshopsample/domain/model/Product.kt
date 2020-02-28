package com.example.eshopsample.domain.model

data class Product(
    val id: Int,
    val manufacturer: String,
    val image: String,
    val images: List<String>,
    val original_image: String,
    val original_images: List<String>,
    val name : String
)

