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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun QuantitySelector(
    modifier: Modifier = Modifier,
    quantity: String,
    onValueChange: (String) -> Unit,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit
) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        FilledIconButton(
            modifier = Modifier.size(30.dp),
            onClick = onIncrease, colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Primary,
            ),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Icon(painterResource(id = R.drawable.ic_minus), contentDescription = "Minus")
        }
        OutlinedTextField(
            value = "1",
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = Tertiary,
                focusedContainerColor = Tertiary,
                unfocusedContainerColor = Tertiary,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            shape = RoundedCornerShape(percent = 10),
            modifier = Modifier
                .width(50.dp)
                .height(30.dp)
                .align(Alignment.CenterVertically)
                .padding(5.dp)
        )
        FilledIconButton(
            modifier = Modifier.size(30.dp),
            onClick = onDecrease, colors = IconButtonDefaults.iconButtonColors(
                contentColor = Color.White,
                containerColor = Primary,
            ),
            shape = RoundedCornerShape(percent = 10)
        ) {
            Icon(painterResource(id = R.drawable.ic_add), contentDescription = "Minus")
        }
    }
}

@Preview
@Composable
private fun DetailSelectorPrev() {
    QuantitySelector(quantity = "1", onIncrease = {}, onDecrease = {}, onValueChange = {})
}