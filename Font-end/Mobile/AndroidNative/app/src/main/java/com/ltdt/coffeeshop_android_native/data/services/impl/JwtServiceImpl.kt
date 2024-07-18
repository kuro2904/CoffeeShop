package com.ltdt.coffeeshop_android_native.data.services.impl

import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import java.util.Base64

class JwtServiceImpl: JwtService {

    override fun getUserId(token: Token): String {
        val payloadString = extractTokenPayload(token)
        return payloadString
    }

    private fun extractTokenPayload(token: Token): String {
        val parts = token.token.split(".")
        return try {
            val charset = charset("UTF-8")
            val payload = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)
            payload.split("\"userId\":\"")[1].replace("\"}","")
        } catch (e: Exception) {
            "Error parsing JWT: $e"
        }
    }
}