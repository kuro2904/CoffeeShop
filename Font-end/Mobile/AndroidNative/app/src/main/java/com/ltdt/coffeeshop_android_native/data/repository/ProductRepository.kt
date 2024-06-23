package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.Product

interface ProductRepository {
    suspend fun getAll():List<Product>
}