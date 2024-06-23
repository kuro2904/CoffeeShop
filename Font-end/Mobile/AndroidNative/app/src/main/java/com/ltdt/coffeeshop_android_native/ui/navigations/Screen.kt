package com.ltdt.coffeeshop_android_native.ui.navigations

sealed class Screen(
    val route: String
) {
    data object HomeScreen: Screen("home_screen")
    data object CartScreen: Screen("cart_screen")
    data object OrderScreen: Screen("order_screen")
    data object PaymentScreen: Screen("payment_screen")
    data object FavoriteScreen: Screen("favorite_screen")
    data object ProductDetailScreen: Screen("product_detail_screen")

}