package com.ltdt.coffeeshop_android_native.data.domains

data class OrderDetail(
    val id: Int?,
    val quantity: Int,
    val price: Double,
    val productDetail: ProductDetail,
    val product: Product,
    val userId: String
)
