package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme

@Composable
fun ModalOrderTime(
    modifier: Modifier = Modifier,
    dateOrder: String,
    onDateClick: () -> Unit,
    timeOrder: String,
    onTimeClick: () -> Unit,
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDateClick() }
                .background(color = Color.DarkGray, shape = RoundedCornerShape(20))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = dateOrder, color = Color.White, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .clickable { onTimeClick() }
                .background(color = Color.DarkGray, shape = RoundedCornerShape(20))
                .border(width = 1.dp, color = Color.Gray, shape = RoundedCornerShape(20)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = timeOrder, color = Color.White, fontSize = 20.sp)
        }
    }
}

@Preview
@Composable
private fun ModalOrderTimePrev() {
    CoffeeShopAndroidNativeTheme {
        ModalOrderTime(
            dateOrder = "12/12/2023",
            timeOrder = "12:00 am",
            onDateClick = {},
            onTimeClick = {}
        )
    }
}