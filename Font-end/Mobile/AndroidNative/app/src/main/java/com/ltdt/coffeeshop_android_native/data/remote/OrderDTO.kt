package com.ltdt.coffeeshop_android_native.data.remote

import com.ltdt.coffeeshop_android_native.data.domains.DeliveryType
import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.domains.OrderStatus
import com.ltdt.coffeeshop_android_native.data.domains.PaymentType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class OrderDTO(
    val id: String?,
    val paymentMethod: String,
    val amount: Double,
    val note: String,
    val orderDate: String,
    val deliveryType: String,
    val address: String,
    val details: List<OrderDetailDTO>,
    val customerId: String,
    val employeeId: String,
    val status: String,
    val receivePerson: String,
    val receivePhoneNumber: String
)

fun OrderDTO.toEntity() = Order(
    id = id,
    paymentMethod = PaymentType.valueOf(paymentMethod),
    amount = amount,
    note = note,
    orderDate = LocalDateTime.parse(
        orderDate,
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    ),
    deliveryType = DeliveryType.valueOf(deliveryType),
    address = address,
    details = details.map { it.toEntity() },
    customerId = customerId,
    status = OrderStatus.valueOf(status),
    receivePerson = receivePerson,
    receivePhoneNumber = receivePhoneNumber

)