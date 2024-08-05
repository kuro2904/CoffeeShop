package com.ltdt.coffeeshop_android_native.data.domains

import java.time.LocalDateTime


data class Order(
    val id: String? = "",
    val paymentMethod: PaymentType,
    val amount: Double,
    val note: String,
    var orderDate: LocalDateTime,
    val deliveryType: DeliveryType,
    val address: String,
    val details: List<OrderDetail>,
    val customerId: String,
    val status: OrderStatus,
    val receivePerson: String,
    val receivePhoneNumber: String
)
