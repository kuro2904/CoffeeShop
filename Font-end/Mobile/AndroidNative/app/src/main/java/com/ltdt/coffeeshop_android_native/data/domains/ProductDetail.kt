package com.ltdt.coffeeshop_android_native.data.domains

import com.ltdt.coffeeshop_android_native.data.remote.ProductDetailDTO

data class ProductDetail(
    val id: Int,
    val productId: Int,
    val size: String,
    val price: Double
)

fun ProductDetail.toProductDetailDTO(): ProductDetailDTO = ProductDetailDTO(
    id = id,
    productId = productId,
    size = size,
    price = price

)