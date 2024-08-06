package com.ltdt.coffeeshop_android_native.ui.screens.order_histories

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ltdt.coffeeshop_android_native.ui.screens.order_histories.components.OrderHistoryCard

@Composable
fun OrderHistoryScreen(
    viewModel: OrderHistoryViewModel = hiltViewModel()
) {
    val state by viewModel.state

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error.isNotEmpty() -> {
                Text(text = state.error, modifier = Modifier.align(Alignment.Center))
            }

            else -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    items(
                        items = state.orderHistories,
                    ) { order ->
                        OrderHistoryCard(order = order)
                    }
                }
            }
        }
    }
}
