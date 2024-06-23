package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.Category

interface CategoryRepository {
    suspend fun getAll(): List<Category>
}