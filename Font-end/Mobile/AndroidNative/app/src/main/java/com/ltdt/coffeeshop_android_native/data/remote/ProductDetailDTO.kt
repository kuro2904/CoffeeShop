package com.ltdt.coffeeshop_android_native.data.remote

import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail

data class ProductDetailDTO(
    val id: Int,
    val productId: Int,
    val size: String,
    val price: Double
)

fun ProductDetailDTO.toEntity() = ProductDetail(
    id = id,
    productId = productId,
    size = size,
    price = price
)