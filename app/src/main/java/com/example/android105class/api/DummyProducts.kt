package com.example.android105class.api

import com.example.android105class.dataClass.Product

data class DummyProducts(
    val limit: Int,
    val products: MutableList<Product>,
    val skip: Int,
    val total: Int
)