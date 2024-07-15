package com.ltdt.coffeeshop_android_native.ui.screens.carts.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.ui.screens.products.ProductImage

@Composable
fun OrderDetailCard(modifier: Modifier = Modifier, product: Product) {
    Card(modifier = Modifier.fillMaxSize(), onClick = { /*TODO*/ }) {
        Column {
            Row {
                ProductImage(isShowRating = false, product = product)
                Column {
                    Text(text = product.name)
                    Text(text = product.description)
                }
            }
            LazyColumn {
                
            }
        }
    }
}