package com.example.eshopsample.data.model

data class ProductsByCategoryResponse(
    val success: Int,
    val error: List<String>,
    val data: List<ProductEntity>
)

// Short product DTO
data class ProductEntity(
    val id: Int,
    val manufacturer: String,
    val image: String,
    val images: List<String>,
    val original_image: String,
    val original_images: List<String>,
    val product_description: List<ProductDescription>
)


data class ProductDescription (
    val name : String,
    val description : String
)