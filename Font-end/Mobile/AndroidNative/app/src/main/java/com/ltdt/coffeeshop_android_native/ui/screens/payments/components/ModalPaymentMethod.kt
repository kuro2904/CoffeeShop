package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.data.domains.PaymentType
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun ModalPaymentMethod(
    modifier: Modifier = Modifier,
    paymentMethods: List<PaymentMethodItem>,
    onChangePaymentMethod: (PaymentMethodItem) -> Unit
) {
    LazyColumn(
        modifier
            .padding(10.dp)
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(paymentMethods.size) { index ->
            Box(
                modifier = Modifier
                    .border(
                        BorderStroke(color = Color.White, width = 1.dp),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(shape = RoundedCornerShape(10.dp), color = Primary)
                    .clickable { onChangePaymentMethod(paymentMethods[index]) }
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = paymentMethods[index].icon),
                        contentDescription = "Payment Method Icon",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = paymentMethods[index].title.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }
}

@Preview
@Composable
private fun ModalPaymentMethodPrev() {
    CoffeeShopAndroidNativeTheme {
        ModalPaymentMethod(
            paymentMethods = listOf(
                PaymentMethodItem(PaymentType.COD, R.drawable.ic_cod),
                PaymentMethodItem(PaymentType.MOMO, R.drawable.logo_momo),
                PaymentMethodItem(PaymentType.VNPAY, R.drawable.vnpay_logo),
            ),
            onChangePaymentMethod = {}
        )
    }
}