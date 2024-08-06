package com.ltdt.coffeeshop_android_native.data.domains

import com.ltdt.coffeeshop_android_native.data.remote.OrderDTO
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


data class Order(
    val id: String? = "NULL CHECK",
    val paymentMethod: PaymentType,
    val amount: Double,
    val note: String = "NULL CHECK",
    var orderDate: LocalDateTime,
    val deliveryType: DeliveryType,
    val address: String = "NULL CHECK",
    val details: List<OrderDetail>,
    val customerId: String = "NULL CHECK",
    val status: OrderStatus,
    val receivePerson: String = "NULL CHECK",
    val receivePhoneNumber: String = "NULL CHECK"
)

fun Order.toOrderDTO(): OrderDTO {
    return OrderDTO(
        id = this.id,
        paymentMethod = this.paymentMethod.name,
        amount = this.amount,
        note = this.note,
        orderDate = this.orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")),
        deliveryType = this.deliveryType.name,
        address = this.address,
        details = this.details.map { it.toOrderDetailDTO() },
        customerId = this.customerId,
        employeeId = "",
        status = this.status.name,
        receivePerson = this.receivePerson,
        receivePhoneNumber = this.receivePhoneNumber
    )
}
