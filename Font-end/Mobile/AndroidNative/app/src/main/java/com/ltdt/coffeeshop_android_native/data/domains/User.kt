package com.ltdt.coffeeshop_android_native.data.domains

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val address: String,
    val orderDetails: List<OrderDetail>
)
