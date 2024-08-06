package com.ltdt.coffeeshop_android_native.ui.screens

import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ltdt.coffeeshop_android_native.AuthActivity
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.components.ToolBarComponent
import com.ltdt.coffeeshop_android_native.ui.navigations.ContentNav
import com.ltdt.coffeeshop_android_native.ui.navigations.Screen
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Secondary


@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel(),
    context: Context = LocalContext.current
) {

    val user = viewModel.user.value
    val selectedItemIndex = viewModel.selectedIndex.intValue

    val items = listOf(
        BottomNavigationItem(
            routeName = Screen.HomeScreen.route,
            selectIcon = Icons.Filled.Home,
            unselectIcon = Icons.Outlined.Home,
            hasNews = false,
            title = "Home"
        ),
        BottomNavigationItem(
            routeName = Screen.CartScreen.route,
            selectIcon = Icons.Filled.ShoppingCart,
            unselectIcon = Icons.Outlined.ShoppingCart,
            hasNews = false,
            title = "Cart"
        ),
        BottomNavigationItem(
            routeName = Screen.FavoriteScreen.route,
            selectIcon = Icons.Filled.Favorite,
            unselectIcon = Icons.Outlined.Favorite,
            hasNews = false,
            title = "Favorite"
        ),
        BottomNavigationItem(
            routeName = Screen.OrderHistoryScreen.route,
            selectIcon = Icons.Filled.Notifications,
            unselectIcon = Icons.Outlined.Notifications,
            hasNews = false,
            title = "Orders"
        ),
    )


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = Secondary) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.title) },
                        selected = selectedItemIndex == index,
                        onClick = {
                            viewModel.updateSelectedIndex(index)
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
        },
        topBar = {
            ToolBarComponent(
                modifier = Modifier
                    .background(Secondary)
                    .padding(top = 25.dp),
                leftIcon = painterResource(id = R.drawable.view_cozy),
                rightIcon = Icons.Outlined.Person,
                user = user,
                title = items[selectedItemIndex].title,
                onRightIconClick = {
                    if (user == null) {
                        startActivity(
                            context,
                            Intent(context, AuthActivity::class.java),
                            null
                        )
                    } else {
                        Toast.makeText(context, "Logged in!!", Toast.LENGTH_SHORT).show()
                    }
                })
        },
    ) { innerPadding ->
        ContentNav(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Secondary),
            navController = navController
        )
    }

}

data class BottomNavigationItem(
    val title: String,
    val selectIcon: ImageVector,
    val unselectIcon: ImageVector,
    val hasNews: Boolean,
    val routeName: String
)
