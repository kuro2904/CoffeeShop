package com.ltdt.coffeeshop_android_native.data.remote

import com.ltdt.coffeeshop_android_native.data.domains.Product

class ProductDTO(
    val id: Int,
    val name: String,
    val description: String,
    val rate: Double,
    val rateSum: Long,
    val categoryId: Int,
    val categoryName: String,
    val isActive: Boolean,
    val details: List<ProductDetailDTO>,
    val images: List<String>
)

fun ProductDTO.toEntity() = Product(
    id = id,
    name = name,
    description = description,
    rate = rate,
    categoryId = categoryId,
    categoryName = categoryName,
    isActive = isActive,
    details = details.map { it.toEntity() },
    images = images,
    rateSum = rateSum
)