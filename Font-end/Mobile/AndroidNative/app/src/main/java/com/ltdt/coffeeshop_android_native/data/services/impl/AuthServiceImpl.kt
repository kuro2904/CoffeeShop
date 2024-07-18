package com.ltdt.coffeeshop_android_native.data.services.impl

import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.UserLogin
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import com.ltdt.coffeeshop_android_native.data.services.AuthService
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    val apiService: ApiService
) : AuthService {

    override suspend fun login(loginRequest: UserLogin): Token {
        return apiService.login(loginRequest)
    }

    override suspend fun register(user: UserRegister): Token {
        return apiService.register(user)
    }

}