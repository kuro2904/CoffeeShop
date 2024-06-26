package com.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail
import com.ltdt.coffeeshop_android_native.ui.theme.Primary
import com.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun ProductCard(modifier: Modifier = Modifier, product: Product,onClick: ()-> Unit) {

    Card(
        modifier
            .width(149.dp)
            .height(245.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Tertiary), Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProductImage(
                    modifier = Modifier.fillMaxWidth(),
                    product = product,
                    isShowRating = true
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = product.name,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = product.description,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconTextComponent(
                        width = 30,
                        icon = painterResource(id = R.drawable.dollar_sign),
                        text = "4.2",
                        textSize = 16,
                        iconSize = 16,
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .wrapContentSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .background(color = Primary, shape = RoundedCornerShape(percent = 20))
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "Add",
                                tint = Color.Black,
                                modifier = Modifier
                                    .size(20.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }

    }

}

@Preview
@Composable
fun ProductCardPrev() {

    val details: List<ProductDetail> = listOf(ProductDetail(1, 0, "M", 4.5))
    val images: List<String> = listOf("aAA", "ddd")
    val product = Product(0, "test", 1, "Description Test", "Cate 1", true, details, images)
    ProductCard(product = product, onClick = {})
}