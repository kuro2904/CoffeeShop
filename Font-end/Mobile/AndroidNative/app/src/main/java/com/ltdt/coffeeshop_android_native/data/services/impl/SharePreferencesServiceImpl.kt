package com.ltdt.coffeeshop_android_native.data.services.impl

import android.content.Context
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import javax.inject.Inject

class SharePreferencesServiceImpl @Inject constructor(
    private val context: Context
) : SharePreferencesService {
    override suspend fun saveKey(key: String, value: String) {
    }

    override suspend fun getKey(key: String): String {
        TODO("Not yet implemented")
    }

}