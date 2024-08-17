package com.example.android105class.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ProductService {
    @GET("auth/products")
    suspend fun getAllProducts(): Response<DummyProducts>
}