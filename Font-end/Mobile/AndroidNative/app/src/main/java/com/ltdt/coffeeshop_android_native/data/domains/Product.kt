package com.ltdt.coffeeshop_android_native.data.domains

import com.ltdt.coffeeshop_android_native.data.remote.ProductDTO

data class Product(
    val id: Int,
    val name: String,
    val categoryId: Int,
    val description: String,
    val categoryName: String,
    val isActive: Boolean,
    val rateSum: Long,
    val rate: Double,
    val details: List<ProductDetail>,
    val images: List<String>
)

fun Product.toProductDTO(): ProductDTO = ProductDTO(
    id = id,
    name = name,
    categoryId = categoryId,
    description = description,
    isActive = isActive,
    rate = rate,
    details = details.map { it.toProductDetailDTO() },
    images = images,
    categoryName = categoryName,
    rateSum = rateSum
)