package com.ltdt.coffeeshop_android_native.ui.screens.carts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.ui.components.BoxText
import com.ltdt.coffeeshop_android_native.ui.components.IconTextComponent
import com.ltdt.coffeeshop_android_native.ui.screens.products.ProductImage
import com.ltdt.coffeeshop_android_native.ui.theme.Grayish
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun OrderDetailCard(
    modifier: Modifier = Modifier,
    orderDetails: List<OrderDetail>,
    onIncrease: (OrderDetail) -> Unit,
    onDecrease: (OrderDetail) -> Unit,
) {
    Box(
        modifier = modifier
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Tertiary,
                        Color.Black,
                    )
                ),
                shape = RoundedCornerShape(percent = 10)
            ),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Transparent)
        ) {
            Row {
                ProductImage(isShowRating = false, product = orderDetails[0].product)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(text = orderDetails[0].product.name, color = Color.White, fontSize = 25.sp)
                    Text(
                        text = orderDetails[0].product.description,
                        color = Grayish,
                        fontSize = 20.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    orderDetails.size,
                    key = { index -> orderDetails[index].productDetail.id }) { index ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BoxText(
                            text = orderDetails[index].productDetail.size,
                            textSize = 20,
                            textColor = Color.White,
                            modifier = Modifier
                                .width(80.dp)
                                .height(50.dp),
                        )
                        IconTextComponent(
                            icon = painterResource(id = R.drawable.dollar_sign),
                            iconSize = 25,
                            textSize = 20,
                            text = orderDetails[index].price.toString()
                        )
                        QuantitySelector(
                            orderDetail = orderDetails[index],
                            onIncrease = { onIncrease(orderDetails[index]) },
                            onDecrease = { onDecrease(orderDetails[index]) }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
