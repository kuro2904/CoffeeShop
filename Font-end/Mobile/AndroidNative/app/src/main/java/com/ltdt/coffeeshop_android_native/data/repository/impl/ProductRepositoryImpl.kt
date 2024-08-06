package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.remote.ProductDTO
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {

    override suspend fun getAll(): List<ProductDTO> {
        return apiService.getAllProducts()
    }

    override suspend fun getProductsByCategory(categoryId: Int): List<ProductDTO> {
        return apiService.getProductsByCategory(categoryId)
    }

    override suspend fun getProductById(productId: Int): ProductDTO {
        return apiService.getProductById(productId)
    }
}