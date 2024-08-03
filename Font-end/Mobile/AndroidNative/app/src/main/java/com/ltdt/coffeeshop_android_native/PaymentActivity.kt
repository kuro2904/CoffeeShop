package com.ltdt.coffeeshop_android_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ltdt.coffeeshop_android_native.ui.components.ToolBarComponent
import com.ltdt.coffeeshop_android_native.ui.screens.payments.PaymentScreen
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Secondary
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopAndroidNativeTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        ToolBarComponent(
                            modifier = Modifier
                                .background(Secondary)
                                .padding(top = 25.dp),
                            leftIcon = painterResource(id = R.drawable.view_cozy),
                            rightIcon = Icons.Filled.Home,
                            title = "Payment",
                            user = null
                        )
                    }
                ) { innerPadding ->
                    PaymentScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Secondary)
                    )
                }
            }
        }
    }
}
