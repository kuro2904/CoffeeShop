package com.ltdt.coffeeshop_android_native.ui.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.common.Constants.API_HOST
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.ProductDetail
import com.ltdt.coffeeshop_android_native.ui.components.IconTextComponent

@Composable
fun ProductImage(modifier: Modifier = Modifier, product: Product? = null, isShowRating: Boolean) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .clip(
                RoundedCornerShape(
                    percent = 10
                )
            ),
        contentAlignment = Alignment.Center,
    )
    {
        if (product != null && product.images.isNotEmpty())
            AsyncImage(
                model = "http://$API_HOST:8080/api/v1/images/${product.images[0]}",
                contentDescription = "Product Image",
                modifier = Modifier.size(125.dp)
            )
        else
            Image(
                painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "Product Image",

                )

        if (isShowRating) {
            IconTextComponent(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .background(
                        shape = RoundedCornerShape(
                            bottomStart = 20.dp,
                        ),
                        color = Color.Black.copy(alpha = 0.5f)
                    ),
                text = product?.rate.toString(),
                icon = painterResource(id = R.drawable.ic_star),
                iconSize = 12,
                textSize = 12
            )
        }
    }
}


@Preview
@Composable
fun ProductImagePrev() {
    val details: List<ProductDetail> = listOf(ProductDetail(1, 0, "M", 4.5))
    val images: List<String> = listOf("aAA", "ddd")
    val product = Product(
        0,
        "test",
        1,
        "Description Test",
        "Cate 1",
        true,
        details = details,
        images = images
    )
    ProductImage(isShowRating = true)
}