package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.remote.ProductDTO

interface ProductRepository {
    suspend fun getAll(): List<ProductDTO>
    suspend fun getProductsByCategory(categoryId: Int): List<ProductDTO>
    suspend fun getProductById(productId: Int): ProductDTO
}