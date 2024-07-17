package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.Token

interface JwtService {
    fun getUserId(token: Token): String
    
}