package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail
import com.ltdt.coffeeshop_android_native.data.repository.CartRepository

class CartRepositoryImpl : CartRepository {

    private val cartItems = mutableListOf<OrderDetail>()

    override fun addToCart(
        product: Product,
        detail: ProductDetail,
        quantity: Int,
    ) {
        if (cartItems.any { it.productDetail.id == detail.id }) {
            cartItems.find { it.productDetail.id == detail.id }?.let {
                it.quantity += quantity
                it.price += detail.price * quantity
            }
        } else {
            cartItems.add(
                OrderDetail(
                    product = product,
                    productDetail = detail,
                    quantity = quantity,
                    price = detail.price * quantity
                )
            )
        }
    }

    override fun updateQuantity(orderDetail: OrderDetail, quantity: Int) {
        cartItems.find { it.productDetail.id == orderDetail.productDetail.id }?.let {
            it.quantity = quantity
            it.price = orderDetail.productDetail.price * quantity
        }
    }

    override fun removeFromCart(orderDetail: OrderDetail) {
        cartItems.remove(orderDetail)
    }

    override fun getCartItems(): List<OrderDetail> {
        return cartItems
    }

    override fun getTotalPrice(): Double {
        return cartItems.sumOf { it.price }
    }

    override fun clearCart() {
        cartItems.clear()
    }
}