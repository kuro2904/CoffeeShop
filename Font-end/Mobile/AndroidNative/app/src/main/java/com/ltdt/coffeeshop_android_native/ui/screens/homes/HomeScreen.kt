package com.ltdt.coffeeshop_android_native.ui.screens.homes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.ui.navigations.Screen
import com.ltdt.coffeeshop_android_native.ui.screens.products.ProductCard
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .padding(top = 10.dp)
    ) {
        item {

            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Left,
                text = "Find the best \ncoffee for you",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,

                )
            Spacer(modifier = Modifier.height(30.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = viewModel.searchInputText.value,
                onValueChange = { viewModel.searchInputText.value = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                placeholder = { Text(text = "Find your Coffee...") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Tertiary,
                    unfocusedContainerColor = Tertiary,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(percent = 30),
            )
            Spacer(modifier = Modifier.height(30.dp))
            ScrollableTabRow(
                selectedTabIndex = viewModel.tabSelectedIndex.intValue,
                modifier = Modifier.fillMaxWidth(),
                containerColor = Color.Transparent,
                edgePadding = 0.dp,
                divider = {

                },
                indicator = { tabPositions ->
                    Box(
                        modifier = Modifier
                            .tabIndicatorOffset(tabPositions[viewModel.tabSelectedIndex.intValue])
                            .background(color = Primary, shape = CircleShape)
                            .size(5.dp)
                    )
                },
            ) {
                Tab(
                    selected = 0 == viewModel.tabSelectedIndex.intValue,
                    onClick = {
                        viewModel.tabSelectedIndex.intValue = 0
                        viewModel.productFilteredByCategory(0)
                    },
                    text = {
                        Text(
                            text = "All",
                            fontSize = 20.sp,
                            color = if (0 == viewModel.tabSelectedIndex.intValue) Primary else Gray
                        )
                    }
                )
                viewModel.categoriesState.value.categories.forEachIndexed { index, category ->
                    Tab(
                        selected = index + 1 == viewModel.tabSelectedIndex.intValue,
                        onClick = {
                            viewModel.tabSelectedIndex.intValue = index + 1
                            viewModel.productFilteredByCategory(index + 1)
                        },
                        text = {
                            Text(
                                text = category.name,
                                fontSize = 20.sp,
                                color = if (index + 1 == viewModel.tabSelectedIndex.intValue) Primary else Gray
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                userScrollEnabled = true,
                verticalAlignment = Alignment.CenterVertically,
                contentPadding = PaddingValues(10.dp)
            ) {
                items(
                    viewModel.productsState.value.products,
                    key = { item -> item.id }
                ) { item: Product ->
                    ProductCard(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        product = item,
                        onClick = { navController.navigate(Screen.ProductDetailScreen.route.plus("?productId=${item.id}")) })
                }
            }


            if (viewModel.categoriesState.value.isLoading) {
                CircularProgressIndicator()
            }

            if (viewModel.categoriesState.value.error.isNotBlank()) {
                Text(text = viewModel.categoriesState.value.error)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Left,
                text = "Coffee bean",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                userScrollEnabled = true,
                contentPadding = PaddingValues(10.dp)
            ) {
                items(
                    viewModel.productsState.value.products.filter {
//                        it.categoryName.equals("BEAN")
                        it.categoryId == 1
                    },
                    key = { item -> item.id }) { item: Product ->
                    ProductCard(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        product = item,
                        onClick = {
                            navController.navigate(Screen.ProductDetailScreen.route.plus("?productId=${item.id}"))
                        },
                        isShowRate = false
                    )
                }
            }
        }
    }
}
