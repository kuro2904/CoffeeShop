package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun ModalDeliveryUserInfo(
    modifier: Modifier = Modifier,
    userName: String,
    onUserNameChange: (String) -> Unit,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onClickSave: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.Transparent),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName,
            onValueChange = onUserNameChange,
            placeholder = { Text(text = userName) },
            shape = RoundedCornerShape(10),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedContainerColor = Gray,
                unfocusedLabelColor = Grayish,
                unfocusedBorderColor = Grayish,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.Black,

                ),
            label = {
                Text(text = "Name")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            shape = RoundedCornerShape(10),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedContainerColor = Gray,
                unfocusedLabelColor = Grayish,
                unfocusedBorderColor = Grayish,
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.Black,
            ),
            label = {
                Text(text = "Phone number")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = onClickSave,
            colors = ButtonDefaults.buttonColors(
                containerColor = Primary,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(10),
        ) {
            Text(text = "Save")
        }
    }


}

@Preview
@Composable
private fun ModalDeliveryUserInfoPrev() {
    CoffeeShopAndroidNativeTheme {
        ModalDeliveryUserInfo(
            userName = "aasd",
            onUserNameChange = { },
            phoneNumber = "12323",
            onPhoneNumberChange = { },
            onClickSave = {}
        )
    }
}