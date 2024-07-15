package com.ltdt.coffeeshop_android_native.ui.screens.products

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.common.Constants.API_HOST
import com.ltdt.coffeeshop_android_native.ui.components.BoxIcon
import com.ltdt.coffeeshop_android_native.ui.components.BoxText
import com.ltdt.coffeeshop_android_native.ui.components.IconTextComponent
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    productId: Int?,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState =
            rememberPagerState(pageCount = {
                viewModel.productState.value.product?.images?.size ?: 0
            })
        if (viewModel.productState.value.product != null) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
            ) { index ->
                Box {
                    AsyncImage(
                        model = "http://$API_HOST:8080/api/v1/images/${viewModel.productState.value.product!!.images[index]}",
                        contentDescription = "Image test",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds,
                        alignment = Alignment.Center,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                            .height(IntrinsicSize.Min),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()

                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = viewModel.productState.value.product?.name ?: "Unknown",
                                    fontSize = 20.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.SemiBold
                                )
                                IconTextComponent(
                                    icon = painterResource(id = R.drawable.ic_star),
                                    iconSize = 25,
                                    textSize = 18,
                                    text = viewModel.productState.value.product?.rate.toString()
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(0.7f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    BoxIcon(
                                        icon = painterResource(id = R.drawable.ic_star),
                                        iconSize = 30,
                                        text = "Bean",
                                        textSize = 10
                                    )
                                    BoxIcon(
                                        icon = painterResource(id = R.drawable.ic_star),
                                        iconSize = 30,
                                        text = "Bean",
                                        textSize = 10
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                BoxText(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "Medium Roasted",
                                    textSize = 18
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Description",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Grayish
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = viewModel.productState.value.product?.description
                            ?: "Don't have anything!",
                        fontSize = 12.sp,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = "Size",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Grayish
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    LazyRow(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        item {
                            viewModel.productState.value.product?.details?.forEach {
                                OutlinedIconToggleButton(
                                    checked = true,
                                    onCheckedChange = { },
                                    shape = RoundedCornerShape(percent = 20),
                                    colors = IconButtonDefaults.outlinedIconToggleButtonColors(
                                        contentColor = Grayish,
                                        checkedContainerColor = Primary,
                                        checkedContentColor = Color.White,
                                        containerColor = Tertiary,
                                    ),
                                    modifier = Modifier
                                        .height(40.dp),
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = it.size,
                                        fontSize = 12.sp,
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier.weight(0.4f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Price",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Grayish
                            )
                            IconTextComponent(
                                icon = painterResource(id = R.drawable.dollar_sign),
                                iconSize = 25,
                                textSize = 20,
                                text = viewModel.productState.value.product?.details?.get(0)?.price.toString()
                            )
                        }
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.weight(0.6f),
                            shape = RoundedCornerShape(percent = 20),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Primary,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Add to cart", color = Color.White)
                        }
                    }

                }
            }

        }
    }
}

//@Preview
//@Composable
//fun ProductDetailScreenPrev() {
//    ProductDetailScreen()
//}