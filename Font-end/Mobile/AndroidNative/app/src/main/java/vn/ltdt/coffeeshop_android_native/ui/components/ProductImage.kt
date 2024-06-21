package vn.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.ltdt.coffeeshop_android_native.R
import vn.ltdt.coffeeshop_android_native.data.domains.Product
import vn.ltdt.coffeeshop_android_native.data.domains.ProductDetail

@Composable
fun ProductImage(modifier: Modifier = Modifier, product: Product, isShowRating: Boolean) {
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
//      AsyncImage(model = "http://$API_HOST:8080/api/v1/images/${product.images[0]}", contentDescription = "Product Image")
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Product Image",

            )
        if (isShowRating) {
            IconTextComponent(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(0.dp, 5.dp, 5.dp, 0.dp),
                width = 50,
                text = "4.5",
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
    val product = Product(0, "test", 1, "Description Test", "Cate 1", true, details, images)
    ProductImage(product = product, isShowRating = true)
}