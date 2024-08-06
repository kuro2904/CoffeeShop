package com.ltdt.coffeeshop_android_native.ui.navigations

sealed class Screen(
    val route: String
) {

    data object HomeScreen : Screen("home_screen")
    data object CartScreen : Screen("cart_screen")
    data object OrderHistoryScreen : Screen("order_history_screen")
    data object PaymentScreen : Screen("payment_screen")
    data object FavoriteScreen : Screen("favorite_screen")
    data object ProductDetailScreen : Screen("product_detail_screen")
    data object LoginScreen : Screen("login_screen")
    data object RegisterScreen : Screen("register_screen")
    data object OrderReviewScreen : Screen("order_preview_screen")
}