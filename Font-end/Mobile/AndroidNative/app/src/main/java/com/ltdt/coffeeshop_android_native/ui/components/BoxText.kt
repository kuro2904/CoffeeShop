package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun BoxText(
    modifier: Modifier = Modifier,
    text: String,
    textSize: Int,
    textColor: Color = Grayish,
    backgroundColor: Color = Tertiary
) {
    Box(
        modifier
            .height(44.dp)
            .background(color = backgroundColor, shape = RoundedCornerShape(percent = 20)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = textSize.sp,
            color = textColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }
}