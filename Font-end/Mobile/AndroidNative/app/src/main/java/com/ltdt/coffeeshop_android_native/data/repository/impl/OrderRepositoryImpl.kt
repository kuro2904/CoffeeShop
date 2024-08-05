package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : OrderRepository {

    override suspend fun placeOrder(order: Order, token: String): Order {
        return apiService.placeOrder(order, token)
    }

    override suspend fun getUserOrders(userId: String): List<Order> {
        return apiService.getUserOrders(userId)
    }

}