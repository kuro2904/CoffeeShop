package com.ltdt.coffeeshop_android_native.data.domains

data class OrderDetail(
    var quantity: Int,
    var price: Double,
    val productDetail: ProductDetail,
    val product: Product,
)
