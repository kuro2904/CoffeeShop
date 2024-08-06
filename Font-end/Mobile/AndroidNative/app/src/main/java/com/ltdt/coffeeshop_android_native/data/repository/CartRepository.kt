package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail

interface CartRepository {
    fun addToCart(
        product: Product,
        detail: ProductDetail,
        quantity: Int
    )

    fun updateQuantity(orderDetail: OrderDetail, quantity: Int)
    fun removeFromCart(orderDetail: OrderDetail)
    fun getCartItems(): List<OrderDetail>
    fun getTotalPrice(): Double
    fun clearCart()
}