package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister

interface AuthService {
    suspend fun login(email: String, password: String): Token
    suspend fun register(user: UserRegister): Token
}