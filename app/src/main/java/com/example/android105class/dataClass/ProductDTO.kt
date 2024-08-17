package com.example.android105class.dataClass

data class ProductDTO(
    val id:Int,
    val category: String,
    val description: String,
    val price: Double,
    val thumbnail: String,
    val title: String,
    var favStatus: Boolean = false
)