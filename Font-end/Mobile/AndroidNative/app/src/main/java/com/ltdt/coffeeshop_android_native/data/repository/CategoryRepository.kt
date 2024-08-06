package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.remote.CategoryDTO

interface CategoryRepository {
    suspend fun getAll(): List<CategoryDTO>
}