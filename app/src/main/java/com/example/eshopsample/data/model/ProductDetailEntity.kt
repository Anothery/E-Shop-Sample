package com.example.eshopsample.data.model

//Shortened ProductDetail DTO
data class ProductDetailEntity(
    val category: List<List<CategoryEntity>>,
    val manufacturer: String,
    val currency_code: String,
    val currency_id: Int,
    val currency_value: String,
    val date_added: String,
    val date_available: String,
    val date_modified: String,
    val discounts: List<Any>,
    val ean: String,
    val filters: List<Any>,
    val height: String,
    val id: Int,
    val image: String,
    val images: List<String>,
    val isbn: String,
    val jan: String,
    val length: String,
    val length_class: String,
    val length_class_id: Int,
    val location: String,
    val minimum: Int,
    val model: String,
    val mpn: String,
    val options: List<Any>,
    val original_image: String,
    val points: Int,
    val price: Double,
    val price_formated: String,
    val product_description: List<ProductDetailDescription>,
    val product_relateds: List<String>,
    val quantity: Int,
    val rating: Int,
    val shipping: Int,
    val sku: String,
    val sort_order: Int,
    val special: List<Any>,
    val status: Int,
    val stock_status: String,
    val stock_status_id: Int,
    val stores: List<Int>,
    val subtract: Int,
    val tax_class_id: Int,
    val tax_value: Double,
    val upc: String,
    val viewed: Int,
    val weight: String,
    val weight_class: String,
    val weight_class_id: Int,
    val width: String
)
