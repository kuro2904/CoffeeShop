package com.ltdt.coffeeshop_android_native.data.domains

data class UserRegister(
    val name: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val address: String = "",
    val avatar: String = ""
)