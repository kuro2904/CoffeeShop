package com.ltdt.coffeeshop_android_native.data.domains

import com.ltdt.coffeeshop_android_native.data.remote.CategoryDTO

data class Category(
    val id: Int,
    val name: String,
    val type: String
)

fun Category.toCategoryDTO(): CategoryDTO = CategoryDTO(
    id = id,
    name = name,
    type = type
)