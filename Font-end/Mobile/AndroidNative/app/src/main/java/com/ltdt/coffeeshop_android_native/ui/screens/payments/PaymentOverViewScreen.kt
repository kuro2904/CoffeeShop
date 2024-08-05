package com.ltdt.coffeeshop_android_native.ui.screens.payments

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.OrderOverViewCardItem
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun PaymentOverview(
    modifier: Modifier = Modifier,
    viewModel: PaymentOverViewViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {

    val order by viewModel.order

    Box(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Order date",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = " ${order?.orderDate?.hour}:${order?.orderDate?.minute} ${order?.orderDate?.dayOfMonth}/${order?.orderDate?.monthValue}/${order?.orderDate?.year}",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Total Amount",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(text = "${order?.amount} VND", fontSize = 16.sp, color = Primary)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            order?.details?.groupBy { it.product }?.forEach { (product, orderDetails) ->
                OrderOverViewCardItem(product = product, orderDetails = orderDetails)
            }
        }
        TextButton(
            onClick = {
                viewModel.placeOrder()
                if (viewModel.state.intValue == 1) (context as Activity).finish()
            },
            modifier = Modifier.align(Alignment.BottomEnd),
            shape = RoundedCornerShape(20),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Buy",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
private fun PaymentOverViewPrev() {
    CoffeeShopAndroidNativeTheme {
        PaymentOverview()
    }
}