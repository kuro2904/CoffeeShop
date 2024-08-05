package com.ltdt.coffeeshop_android_native.ui.screens.order_histories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ltdt.coffeeshop_android_native.ui.screens.payments.PaymentOverview

@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val orderHistories by viewModel.orderHistories
    Box(modifier = Modifier.fillMaxSize()) {
        when (state.value) {
            0 -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            1 -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(
                        count = orderHistories.size,
                        key = {
                            orderHistories[it].id ?: 0
                        },
                        itemContent = {
                            PaymentOverview()
                        }
                    )
                }
            }

            -1 -> {
                Text(text = "Something went wrong", modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}