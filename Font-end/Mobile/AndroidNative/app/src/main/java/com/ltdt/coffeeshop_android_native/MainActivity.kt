package com.ltdt.coffeeshop_android_native

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ltdt.coffeeshop_android_native.ui.navigations.ContentNav
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Secondary
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector,
    val hasNews: Boolean,
    val routeName: String
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopAndroidNativeTheme {
                val navController = rememberNavController()
                MainScreen(navController, context = this)
            }
        }
    }
}


@Composable
fun MainScreen(navController: NavHostController, context: Context) {
    val items = listOf(
        BottomNavigationItem(
            routeName = "home_screen",
            selectIcon = Icons.Filled.Home,
            unselectIcon = Icons.Outlined.Home,
            hasNews = false,
            title = "Home"
        ),
        BottomNavigationItem(
            routeName = "cart_screen",
            selectIcon = Icons.Filled.ShoppingCart,
            unselectIcon = Icons.Outlined.ShoppingCart,
            hasNews = false,
            title = "Cart"
        ),
        BottomNavigationItem(
            routeName = "favorite_screen",
            selectIcon = Icons.Filled.Favorite,
            unselectIcon = Icons.Outlined.Favorite,
            hasNews = false,
            title = "Favorite"
        ),
        BottomNavigationItem(
            routeName = "order_screen",
            selectIcon = Icons.Filled.Notifications,
            unselectIcon = Icons.Outlined.Notifications,
            hasNews = false,
            title = "Orders"
        ),
    )

    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Secondary) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.routeName)
                        },
                        icon = {
                            BadgedBox(badge = { if (item.hasNews) Badge() }) {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectIcon
                                    } else {
                                        item.unselectIcon
                                    },
                                    contentDescription = item.title
                                )
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Primary,
                            unselectedIconColor = Gray,
                            indicatorColor = Secondary,
                            selectedTextColor = Primary,
                            unselectedTextColor = Gray
                        )
                    )
                }

            }
        }
    ) { innerPadding ->
        ContentNav(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Secondary),
            navController = navController,
            context = context
        )
    }
}
