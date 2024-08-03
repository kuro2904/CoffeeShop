package com.ltdt.coffeeshop_android_native.ui.screens.payments

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.ModalDeliveryUserInfo
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.ModalPaymentMethod
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.OrderReviewCard
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.UserDeliveryInfoCard
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Secondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentScreen(
    modifier: Modifier = Modifier,
    viewModel: PaymentViewModel = hiltViewModel()
) {
    val deliveryType by viewModel.deliveryType
    val note by viewModel.note
    val state by viewModel.state
    val user by viewModel.user
    val orders by viewModel.orders
    val paymentMethod by viewModel.paymentMethod
    val showBottomSheet by viewModel.modalSheetState
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Secondary)
    ) {
        when (state) {
            0 -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            -1 -> Text(text = "Something went wrong", modifier = Modifier.align(Alignment.Center))
            1 -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    userScrollEnabled = true,
                ) {
                    item {
                        UserDeliveryInfoCard(
                            deliveryType = deliveryType,
                            changeDeliType = viewModel::changeDeliType,
                            note = note,
                            onNoteChange = viewModel::onNoteChange,
                            user = user,
                            onClickChangeUserInfo = { viewModel.openModalState(2) },
                            onClickChangeAddress = { viewModel.openModalState(1) },
                            onClickChangeTime = { viewModel.openModalState(3) }
                        )
                    }
                    item {
                        OrderReviewCard(orderDetails = orders)
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Total:",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = viewModel.getTotalPrice().toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    item {
                        Column {
                            Text(
                                text = "Payment Method",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = Color.White
                            )
                            Box(
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                                    .height(50.dp)
                                    .border(BorderStroke(1.dp, Color.White), RectangleShape)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                        .clickable { viewModel.openModalState(4) },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(
                                        text = paymentMethod.name,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp,
                                        color = Color.White
                                    )
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }

                    }

                }
                TextButton(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp),
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Primary,
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = "Place Order", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                }

            }
        }
        if (showBottomSheet.isShow) {
            ModalBottomSheet(
                modifier = Modifier.background(Secondary),
                onDismissRequest = { viewModel.closeModalState() },
                sheetState = sheetState,
            ) {
                when (showBottomSheet.action) {
                    1 -> Text(text = "Address Change")
                    2 -> {
                        var userTemp by remember { mutableStateOf(user) }
                        ModalDeliveryUserInfo(
                            userName = userTemp?.name ?: "Unknown",
                            onUserNameChange = { userTemp = userTemp?.copy(name = it) },
                            phoneNumber = userTemp?.phoneNumber ?: "Unknown",
                            onPhoneNumberChange = { userTemp = userTemp?.copy(phoneNumber = it) },
                            onClickSave = {
                                viewModel.changeUserInfo(
                                    userTemp?.name ?: user?.name ?: "Unknown",
                                    userTemp?.phoneNumber ?: user?.phoneNumber ?: "Unknown"
                                )
                                viewModel.closeModalState()
                            }
                        )
                    }

                    3 -> {
                        //Todo: Modal Order Time
                    }

                    4 -> {
                        ModalPaymentMethod(paymentMethods = viewModel.paymentMethodList) { paymentMethod ->
                            viewModel.changePaymentMethod(paymentMethod.title)
                            viewModel.closeModalState()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun PaymentScreenPrev() {
    CoffeeShopAndroidNativeTheme {
        PaymentScreen()
    }
}