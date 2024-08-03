package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.User

interface UserRepository {
    suspend fun getUser(userId: String): User
}