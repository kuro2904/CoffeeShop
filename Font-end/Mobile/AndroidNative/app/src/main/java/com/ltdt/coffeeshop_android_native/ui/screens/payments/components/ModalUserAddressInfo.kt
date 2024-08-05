package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme

@Composable
fun ModalUserAddressInfo(modifier: Modifier = Modifier, userAddress: String?) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Address",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Text(
            text = userAddress ?: "Unknown",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )

    }
}

@Preview
@Composable
private fun ModalUserAddressInfoPrev() {
    CoffeeShopAndroidNativeTheme {
        ModalUserAddressInfo(userAddress = "1223 HCM city")
    }
}