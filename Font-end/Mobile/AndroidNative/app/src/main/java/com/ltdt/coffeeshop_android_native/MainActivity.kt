package com.ltdt.coffeeshop_android_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.ltdt.coffeeshop_android_native.ui.screens.MainScreen
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopAndroidNativeTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}


