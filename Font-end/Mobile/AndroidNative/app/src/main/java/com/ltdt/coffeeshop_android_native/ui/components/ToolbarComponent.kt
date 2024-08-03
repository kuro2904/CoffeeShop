package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.common.Constants.API_HOST
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun ToolBarComponent(
    modifier: Modifier = Modifier,
    leftIcon: Painter,
    rightIcon: ImageVector,
    user: User?,
    title: String = "",
    onLeftIconClick: () -> Unit = {},
    onRightIconClick: () -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = onLeftIconClick,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(color = Tertiary, shape = RoundedCornerShape(percent = 20))
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(Grayish, Grayish)
                        ),
                        shape = RoundedCornerShape(percent = 20)
                    )
            ) {
                Icon(
                    painter = leftIcon,
                    contentDescription = "Add",
                    tint = Grayish,
                    modifier = Modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }
        }

        Text(
            text = title,
            fontSize = 18.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = onRightIconClick,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .background(color = Tertiary, shape = RoundedCornerShape(percent = 20))
                    .border(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(Color.White, Color.White)
                        ),
                        shape = RoundedCornerShape(percent = 20)
                    )
            ) {
                if (user?.avatarUrl == null) {
                    Icon(
                        imageVector = rightIcon,
                        contentDescription = "Add",
                        tint = Color.White,
                        modifier = Modifier
                            .size(20.dp)
                            .align(Alignment.Center)
                    )
                } else {
                    AsyncImage(
                        model = "http://$API_HOST:8080/api/v1/images/${user.avatarUrl}",
                        contentDescription = "user avatar",
                        contentScale = ContentScale.Fit,
                        alignment = Alignment.Center,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ToolBarComponentPrev() {
    ToolBarComponent(
        leftIcon = painterResource(id = R.drawable.view_cozy),
        rightIcon = Icons.Outlined.Home,
        title = "Home",
        user = null
    )
}

