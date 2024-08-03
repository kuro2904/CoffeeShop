package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail

@Composable
fun OrderReviewCard(modifier: Modifier = Modifier, orderDetails: List<OrderDetail>) {
    Column(
        modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Order Summary",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        orderDetails.forEach { orderDetail ->
            OrderItem(orderDetail = orderDetail)
        }
    }
}
