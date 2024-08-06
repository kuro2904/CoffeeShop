package com.ltdt.coffeeshop_android_native.ui.screens.carts

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.ltdt.coffeeshop_android_native.PaymentActivity
import com.ltdt.coffeeshop_android_native.ui.navigations.Screen
import com.ltdt.coffeeshop_android_native.ui.screens.carts.components.CheckoutFooter
import com.ltdt.coffeeshop_android_native.ui.screens.carts.components.OrderDetailCard

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = hiltViewModel(),
    navController: NavController,
    context: Context = LocalContext.current
) {
    val cartItems = viewModel.cartItems.collectAsState().value

    Box(modifier = Modifier) {
        Column(
            modifier = modifier
                .padding(horizontal = 10.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (cartItems.isEmpty()) {
                Text(text = "Cart is empty", fontSize = 30.sp, color = Color.White)
            } else {
                cartItems.groupBy { it.product }.forEach { (_, orderDetails) ->
                    OrderDetailCard(
                        orderDetails = orderDetails,
                        onIncrease = { viewModel.increaseQuantity(it) },
                        onDecrease = { viewModel.decreaseQuantity(it) },
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
        CheckoutFooter(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            price = viewModel.getTotalPrice(),
            placeOrder = {
                if (cartItems.isNotEmpty()) context.startActivity(
                    Intent(
                        context,
                        PaymentActivity::class.java
                    )
                )
            })
    }
}
