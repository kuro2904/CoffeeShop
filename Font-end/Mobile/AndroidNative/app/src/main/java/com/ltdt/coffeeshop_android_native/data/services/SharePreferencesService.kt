package com.ltdt.coffeeshop_android_native.data.services

interface SharePreferencesService {
    suspend fun saveKey(key: String, value: String)
    suspend fun getKey(key: String): String

}