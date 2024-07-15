package com.ltdt.coffeeshop_android_native.data.domains

data class Order(
    val paymentMethod: String,
    val amount: Double,
    val note: String,
    val address: String,
    val customerId: String,
    val status: String,
    val details: List<OrderDetail>
)
