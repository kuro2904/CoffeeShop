package com.ltdt.coffeeshop_android_native.data.services

interface JwtService {
    fun getUserId(token: String): String
}