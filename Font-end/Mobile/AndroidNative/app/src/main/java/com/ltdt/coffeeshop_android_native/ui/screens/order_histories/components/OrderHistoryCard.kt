package com.ltdt.coffeeshop_android_native.ui.screens.order_histories.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.data.domains.DeliveryType
import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.domains.OrderStatus
import com.ltdt.coffeeshop_android_native.data.domains.PaymentType
import com.ltdt.coffeeshop_android_native.di.FakeData
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.OrderOverViewCardItem
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary
import java.time.LocalDateTime


@Composable
fun OrderHistoryCard(modifier: Modifier = Modifier, order: Order) {
    var isExpandedState by remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 500, easing = LinearOutSlowInEasing
                )
            )
            .clickable { isExpandedState = !isExpandedState }
            .background(color = Tertiary, shape = RoundedCornerShape(10.dp)),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Text(text = order.receivePerson, color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    Text(text = order.receivePhoneNumber, color = Grayish, fontSize = 20.sp)
                }
                Text(text = "${order.amount} vnd", color = Primary, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            if (isExpandedState) {
                order.details.groupBy { it.product }.forEach { (product, orderDetails) ->
                    OrderOverViewCardItem(product = product, orderDetails = orderDetails)
                }
            }
        }
    }
}

@Preview
@Composable
private fun OrderHistoryCardPrev() {
    CoffeeShopAndroidNativeTheme {
        val order = Order(
            id = "1",
            paymentMethod = PaymentType.COD,
            amount = 100.0,
            note = "Note",
            orderDate = LocalDateTime.now(),
            deliveryType = DeliveryType.DELIVERY,
            address = "Address",
            details = FakeData.orderDetails,
            customerId = "1",
            status = OrderStatus.WAITING,
            receivePerson = "Receive Person",
            receivePhoneNumber = "Receive Phone Number"
        )
        OrderHistoryCard(order = order)
    }
}