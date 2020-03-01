package com.example.eshopsample.domain.model

data class CategoryWithProducts(
    val category: Category,
    val products: List<ProductDetail>
)