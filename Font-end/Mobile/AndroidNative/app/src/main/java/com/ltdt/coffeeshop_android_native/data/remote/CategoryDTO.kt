package com.ltdt.coffeeshop_android_native.data.remote

import com.ltdt.coffeeshop_android_native.data.domains.Category

data class CategoryDTO(
    val id: Int,
    val name: String,
    val type: String
)

fun CategoryDTO.toEntity() = Category(
    id = id,
    name = name,
    type = type
)
