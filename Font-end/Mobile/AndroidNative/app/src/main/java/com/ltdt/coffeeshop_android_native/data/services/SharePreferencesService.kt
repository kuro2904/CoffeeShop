package com.ltdt.coffeeshop_android_native.data.services

interface SharePreferencesService {
    suspend fun saveStringKey(key: String, value: String)
    suspend fun getStringKey(key: String): String?

}