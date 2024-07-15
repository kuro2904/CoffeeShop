package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun ButtonIcon(
    modifier: Modifier,
    icon: Painter,
    onClick: () -> Unit
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(
            percent = 10
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White
        )
    ){
        Icon(painter = icon, contentDescription = "Icon")
    }
}