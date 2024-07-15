package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.Product

interface ProductRepository {
    suspend fun getAll(): List<Product>
    suspend fun getProductsByCategory(categoryId: Int): List<Product>
    suspend fun getProductById(productId: Int): Product
}