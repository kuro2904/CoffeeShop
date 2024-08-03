package com.ltdt.coffeeshop_android_native.ui.screens.carts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun QuantitySelector(
    modifier: Modifier = Modifier,
    orderDetail: OrderDetail,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FilledIconButton(
            modifier = Modifier.size(50.dp),
            onClick = onDecrease,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Primary,
            ),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Icon(painterResource(id = R.drawable.ic_minus), contentDescription = "Minus")
        }
        OutlinedTextField(
            value = orderDetail.quantity.toString(),
            onValueChange = { },
            enabled = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Tertiary,
                focusedContainerColor = Tertiary,
                unfocusedContainerColor = Tertiary,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(percent = 10),
            textStyle = TextStyle.Default.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .width(100.dp)
                .height(60.dp)
                .align(Alignment.CenterVertically)
                .padding(5.dp)
        )
        FilledIconButton(
            modifier = Modifier.size(50.dp),
            onClick = onIncrease,
            colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Primary,
            ),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Icon(painterResource(id = R.drawable.ic_add), contentDescription = "Plus")
        }
    }
}

@Preview
@Composable
private fun QuantitySelectorPreview() {
    val product = Product(
        0,
        "Test",
        0,
        "Des Test",
        "DDD",
        true,
        details = listOf(
            ProductDetail(0, productId = 0, size = "S", price = 5.0)
        ),
        images = listOf()
    )
    val orderDetail = OrderDetail(
        product = product,
        price = 0.0,
        quantity = 0,
        productDetail = product.details[0]
    )
    QuantitySelector(
        orderDetail = orderDetail,
        onIncrease = {
            orderDetail.quantity += 1
        },
        onDecrease = {
            if (orderDetail.quantity > 1) orderDetail.quantity -= 1
        }
    )
}
