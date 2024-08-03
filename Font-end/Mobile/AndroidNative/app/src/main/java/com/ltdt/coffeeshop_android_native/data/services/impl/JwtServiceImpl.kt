package com.ltdt.coffeeshop_android_native.data.services.impl

import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import java.util.Base64

class JwtServiceImpl(private val sharePreferencesService: SharePreferencesService) : JwtService {

    override fun getUserId(token: String): String {
        return extractTokenPayload(token)
    }

    private fun extractTokenPayload(token: String): String {
        val parts = token.split(".")
        return try {
            val charset = charset("UTF-8")
            val payload =
                String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            payload.split("\"userId\":\"")[1].replace("\"}", "")
        } catch (e: Exception) {
            "Error parsing JWT: $e"
        }
    }
}