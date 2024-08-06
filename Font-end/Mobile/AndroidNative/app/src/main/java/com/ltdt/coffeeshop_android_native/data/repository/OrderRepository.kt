package com.ltdt.coffeeshop_android_native.data.repository

import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.remote.OrderDTO

interface OrderRepository {
    suspend fun placeOrder(order: Order, token: String): Boolean
    suspend fun getUserOrders(userId: String): List<OrderDTO>
}