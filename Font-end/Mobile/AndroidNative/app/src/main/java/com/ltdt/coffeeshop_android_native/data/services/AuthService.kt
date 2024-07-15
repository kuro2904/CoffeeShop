package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.UserRegister

interface AuthService {
    suspend fun login(phoneNumber: String, password: String)
    suspend fun register(user: UserRegister)
}