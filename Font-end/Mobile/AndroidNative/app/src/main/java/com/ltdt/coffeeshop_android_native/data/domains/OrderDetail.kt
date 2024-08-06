package com.ltdt.coffeeshop_android_native.data.domains

import com.ltdt.coffeeshop_android_native.data.remote.OrderDetailDTO

data class OrderDetail(
    val productDetail: ProductDetail,
    val product: Product,
    var quantity: Int,
    var price: Double,
)

fun OrderDetail.toOrderDetailDTO(): OrderDetailDTO {
    return OrderDetailDTO(
        productDetail = productDetail.toProductDetailDTO(),
        product = product.toProductDTO(),
        quantity = quantity,
        price = price
    )
}
