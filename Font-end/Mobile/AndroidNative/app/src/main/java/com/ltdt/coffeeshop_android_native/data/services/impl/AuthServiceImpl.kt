package com.ltdt.coffeeshop_android_native.data.services.impl

import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import com.ltdt.coffeeshop_android_native.data.services.AuthService
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    val apiService: ApiService
) : AuthService {

    override suspend fun login(phoneNumber: String, password: String) {

    }

    override suspend fun register(user: UserRegister) {
        apiService.register(user)
    }

}