package com.ltdt.coffeeshop_android_native.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ltdt.coffeeshop_android_native.ui.screens.carts.CartScreen
import com.ltdt.coffeeshop_android_native.ui.screens.favorites.FavoriteScreen
import com.ltdt.coffeeshop_android_native.ui.screens.homes.HomeScreen
import com.ltdt.coffeeshop_android_native.ui.screens.order_histories.OrderHistoryScreen
import com.ltdt.coffeeshop_android_native.ui.screens.products.ProductDetailScreen

@Composable
fun ContentNav(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        route = "main_screen",
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(route = Screen.CartScreen.route) {
            CartScreen()
        }
        composable(route = Screen.FavoriteScreen.route) {
            FavoriteScreen(navController = navController)
        }
        composable(route = Screen.OrderScreen.route) {
            OrderHistoryScreen()
        }
        composable(
            route = Screen.ProductDetailScreen.route.plus("?productId={productId}"),
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) {
            ProductDetailScreen(navController = navController)
        }
    }
}
