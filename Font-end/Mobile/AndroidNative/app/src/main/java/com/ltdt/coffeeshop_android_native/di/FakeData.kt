package com.ltdt.coffeeshop_android_native.di

import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail

object FakeData {

    private val prodDetail1 = ProductDetail(
        id = 1,
        productId = 1,
        size = "M",
        price = 100.0,
    )

    private val prodDetail2 = ProductDetail(
        id = 2,
        productId = 1,
        size = "L",
        price = 150.0,
    )

    val product = Product(
        id = 1,
        name = "Product 1",
        description = "Product 1 description",
        categoryId = 1,
        categoryName = "Category 1",
        isActive = true,
        rate = 4.5,
        rateSum = 100,
        details = listOf(prodDetail1, prodDetail2),
        images = emptyList()
    )

    private val orderDetail1 = OrderDetail(
        productDetail = prodDetail1,
        quantity = 1,
        price = 100.0,
        product = product
    )
    private val orderDetail2 = OrderDetail(
        productDetail = prodDetail2,
        quantity = 3,
        price = 300.0,
        product = product
    )

    val orderDetails: List<OrderDetail> = listOf(orderDetail1, orderDetail2)

}