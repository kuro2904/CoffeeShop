package com.ltdt.coffeeshop_android_native.data.domains

data class OrderDetail(
    val productDetail: ProductDetail,
    val product: Product,
    var quantity: Int,
    var price: Double,
)
