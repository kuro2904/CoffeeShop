package com.ltdt.coffeeshop_android_native.ui.screens.carts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.components.IconTextComponent
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun CheckoutFooter(modifier: Modifier = Modifier, price: Double, placeOrder: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Total Price", color = Grayish, fontSize = 15.sp)
            IconTextComponent(
                icon = painterResource(id = R.drawable.dollar_sign),
                iconSize = 15,
                textSize = 20,
                text = price.toString()
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        ElevatedButton(
            onClick = placeOrder,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(percent = 10),
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            )
        ) {
            Text(text = "Pay", fontSize = 18.sp)
        }

    }
}

@Preview
@Composable
private fun CheckoutFooterPrev() {
    CoffeeShopAndroidNativeTheme {
        CheckoutFooter(price = 4.5, placeOrder = {})
    }
}