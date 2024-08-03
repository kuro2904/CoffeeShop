package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.data.domains.DeliveryType
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun UserDeliveryInfoCard(
    deliveryType: DeliveryType,
    changeDeliType: () -> Unit,
    note: String,
    onNoteChange: (String) -> Unit,
    user: User? = null,
    onClickChangeUserInfo: () -> Unit,
    onClickChangeAddress: () -> Unit,
    onClickChangeTime: () -> Unit
) {
    Box(modifier = Modifier) {
        if (deliveryType == DeliveryType.DELIVERY) {
            DeliveryInfoCard(
                changeDeliType = changeDeliType,
                note = note,
                onNoteChange = onNoteChange,
                user = user,
                onClickChangeUserInfo = onClickChangeUserInfo,
                onClickChangeAddress = onClickChangeAddress,
                onClickChangeTime = onClickChangeTime
            )
        } else {
            TakeAwayInfoCard(changeDeliType = changeDeliType, onClickChangeTime = onClickChangeTime)
        }
    }
}

@Composable
fun DeliveryInfoCard(
    changeDeliType: () -> Unit,
    note: String = "",
    onNoteChange: (String) -> Unit,
    user: User?,
    onClickChangeUserInfo: () -> Unit,
    onClickChangeAddress: () -> Unit,
    onClickChangeTime: () -> Unit
) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Delivery",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextButton(
                onClick = changeDeliType,
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "Change")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickChangeAddress() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "Home", color = Color.White)
                Text(text = "123 street", color = Grayish)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
        TextField(
            value = note,
            onValueChange = onNoteChange,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            placeholder = {
                Text(text = "Enter your note...", color = Gray, fontSize = 15.sp)
            },
            shape = RoundedCornerShape(10),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .weight(0.5f)
                .clickable { onClickChangeUserInfo() }) {
                Column {
                    Text(text = user?.name ?: "Unknown", color = Color.White)
                    Text(text = user?.phoneNumber ?: "unknown", color = Grayish)
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                        color = Grayish,
                    )
                }
            }
            VerticalDivider(
                modifier = Modifier
                    .height(60.dp)
                    .padding(horizontal = 5.dp),
                color = Grayish,
                thickness = 0.2.dp
            )
            Box(modifier = Modifier
                .weight(0.5f)
                .clickable { onClickChangeTime() }) {
                Column {
                    Text(text = "asd", color = Color.White)
                    Text(text = "asd", color = Grayish)
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider(
                        color = Grayish,
                    )
                }
            }
        }
    }
}

@Composable
fun TakeAwayInfoCard(changeDeliType: () -> Unit, onClickChangeTime: () -> Unit) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Go to shop",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            TextButton(
                onClick = changeDeliType,
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.White),
            ) {
                Text(text = "Change")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickChangeTime() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "HCM city", color = Color.White)
                Text(text = "123 street", color = Grayish)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "15-30 minutes", color = Color.White)
                Text(text = "As soon as possible", color = Grayish)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
private fun DeliveryInfoCardPrev() {
    CoffeeShopAndroidNativeTheme {
        DeliveryInfoCard(
            changeDeliType = {},
            onNoteChange = {},
            user = null,
            onClickChangeUserInfo = {},
            onClickChangeAddress = {},
            onClickChangeTime = {}
        )
    }
}

@Preview
@Composable
private fun TakeAwayInfoCardPrev() {
    CoffeeShopAndroidNativeTheme {
        TakeAwayInfoCard(changeDeliType = {}, onClickChangeTime = {})
    }
}