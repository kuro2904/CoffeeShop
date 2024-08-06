package com.ltdt.coffeeshop_android_native.ui.screens.payments.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.di.FakeData
import com.ltdt.coffeeshop_android_native.ui.components.IconTextComponent
import com.ltdt.coffeeshop_android_native.ui.screens.products.ProductImage
import com.ltdt.coffeeshop_android_native.ui.theme.CoffeeShopAndroidNativeTheme
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun OrderOverViewCardItem(
    modifier: Modifier = Modifier, product: Product, orderDetails: List<OrderDetail>
) {
    Box(
        modifier
            .padding(10.dp)
            .background(shape = RoundedCornerShape(10), color = Tertiary),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (product.images.isNotEmpty() && product.images[0].isNotBlank()) {
                    ProductImage(
                        isShowRating = false,
                        product = product,
                        modifier = Modifier.size(75.dp)
                    )
                } else {
                    Image(
                        modifier = Modifier
                            .wrapContentSize()
                            .clip(RoundedCornerShape(10)),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Product Image"
                    )
                }

                Text(
                    text = product.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.width(150.dp)
                )

                IconTextComponent(
                    icon = painterResource(id = R.drawable.dollar_sign),
                    iconSize = 20,
                    textSize = 20,
                    text = orderDetails.sumOf { it.price }.toString()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            orderDetails.forEachIndexed { index, _ ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                    ) {
                        Box(
                            modifier = Modifier
                                .height(44.dp)
                                .background(
                                    color = Color(0xFF1E1E1E),
                                    shape = RoundedCornerShape(
                                        topStartPercent = 20,
                                        bottomStartPercent = 20
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = orderDetails[index].productDetail.size,
                                fontSize = 20.sp,
                                color = Grayish,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .height(44.dp)
                                .background(
                                    color = Color(0xFF1E1E1E),
                                    shape = RoundedCornerShape(
                                        topEndPercent = 20,
                                        bottomEndPercent = 20
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            IconTextComponent(
                                icon = painterResource(id = R.drawable.dollar_sign),
                                iconSize = 20,
                                textSize = 20,
                                text = orderDetails[index].price.toString(),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }

                    Row {
                        Text(
                            text = "X",
                            fontSize = 20.sp,
                            color = Primary,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = orderDetails[index].quantity.toString(),
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = orderDetails[index].price.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Primary
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Preview
@Composable
private fun OrderOverViewCardItemPrev() {
    CoffeeShopAndroidNativeTheme {
        OrderOverViewCardItem(product = FakeData.product, orderDetails = FakeData.orderDetails)
    }
}
