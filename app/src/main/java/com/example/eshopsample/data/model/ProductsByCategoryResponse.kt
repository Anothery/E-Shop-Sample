package com.example.eshopsample.data.model

data class ProductsByCategoryResponse(
    val success: Int,
    val error: List<String>,
    val data: List<ProductDetailEntity>
)