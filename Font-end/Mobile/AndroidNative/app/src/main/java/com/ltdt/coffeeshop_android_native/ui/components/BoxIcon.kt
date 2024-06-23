package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun BoxIcon(modifier: Modifier = Modifier, icon: Painter) {
    Box(
        modifier.clip(
            shape = RoundedCornerShape(
                percent = 10
            )
        ).background(Tertiary)
    ) {
        Icon(painter = icon, contentDescription = "Product Type", tint = Primary)
    }
}