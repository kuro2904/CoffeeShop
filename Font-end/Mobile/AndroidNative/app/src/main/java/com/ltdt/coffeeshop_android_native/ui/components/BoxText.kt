package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun BoxText(modifier: Modifier = Modifier, text: String, textSize: Int) {
    Box(
        modifier
            .clip(
                shape = RoundedCornerShape(
                    percent = 10
                )
            )
            .background(Tertiary)
    ) {
        Text(text = text, fontSize = textSize.sp, color = Grayish)
    }
}