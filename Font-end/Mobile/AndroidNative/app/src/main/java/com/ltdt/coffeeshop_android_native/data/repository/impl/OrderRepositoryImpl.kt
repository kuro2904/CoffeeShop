package com.ltdt.coffeeshop_android_native.data.repository.impl

import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.toOrderDTO
import com.ltdt.coffeeshop_android_native.data.remote.OrderDTO
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : OrderRepository {

    override suspend fun placeOrder(order: Order, token: String): Boolean {
        return apiService.placeOrder(order.toOrderDTO(), token)
    }

    override suspend fun getUserOrders(userId: String): List<OrderDTO> {
        return apiService.getUserOrders(userId)
    }

}