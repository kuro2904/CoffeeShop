package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun IconTextComponent(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconSize: Int,
    textSize: Int,
    text: String
) {
    Box(
        modifier
            .wrapContentSize(Alignment.Center, unbounded = true)
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {
            Icon(
                painter = icon,
                contentDescription = "Rating icon",
                modifier = Modifier.size(iconSize.dp), // Simplified to use size only
                tint = Primary
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = text,
                style = TextStyle(fontSize = textSize.sp, fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}

@Preview
@Composable
fun RatingBadgePrev() {
    CoffeeShopAndroidNativeTheme {
        IconTextComponent(
            text = "4.5",
            icon = painterResource(id = R.drawable.ic_star),
            iconSize = 12,
            textSize = 12,
        )
    }
}
