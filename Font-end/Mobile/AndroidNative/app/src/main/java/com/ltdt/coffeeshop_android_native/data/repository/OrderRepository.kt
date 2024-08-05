package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.Order

interface OrderRepository {
    suspend fun placeOrder(order: Order, token: String): Order
    suspend fun getUserOrders(userId: String): List<Order>
}