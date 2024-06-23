package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.Category
import retrofit2.http.GET
import com.ltdt.coffeeshop_android_native.data.domains.Product

interface ApiService {

    @GET("products")
    suspend fun getAllProducts():List<Product>

    @GET("categories")
    suspend fun getAllCategories():List<Category>
}