package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.repository.UserRepository
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override suspend fun getUser(userId: String): User {
        return apiService.getUser(userId)
    }
}