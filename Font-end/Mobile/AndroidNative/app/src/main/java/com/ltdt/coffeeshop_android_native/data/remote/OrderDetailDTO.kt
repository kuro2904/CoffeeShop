package com.ltdt.coffeeshop_android_native.data.remote

import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail

data class OrderDetailDTO(
    val productDetail: ProductDetailDTO,
    val product: ProductDTO,
    val quantity: Int,
    val price: Double
)

fun OrderDetailDTO.toEntity() = OrderDetail(
    productDetail = productDetail.toEntity(),
    product = product.toEntity(),
    quantity = quantity,
    price = price
)

