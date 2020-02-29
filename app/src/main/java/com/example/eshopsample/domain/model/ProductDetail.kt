package com.example.eshopsample.domain.model

data class ProductDetail(
    val id: Int,
    val category: List<Category>,
    val manufacturer: String,
    val price_formated: String,
    val model: String,
    val image: String,
    val original_image: String,
    val length: String,
    val length_class: String,
    val weight: String,
    val weight_class: String,
    val width: String,
    val height: String,
    val productName : String,
    val productDescription: String,
    val product_relateds: List<String>
)