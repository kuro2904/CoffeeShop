package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getAll(): List<Product> {
        return apiService.getAllProducts()
    }

    override suspend fun getProductsByCategory(categoryId: Int): List<Product> {
        return apiService.getProductsByCategory(categoryId)
    }

    override suspend fun getProductById(productId: Int): Product {
        return apiService.getProductById(productId)
    }
}