package vn.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.ltdt.coffeeshop_android_native.R
import vn.ltdt.coffeeshop_android_native.ui.theme.Tertiary

@Composable
fun ToolBarComponent(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(percent = 10),
            elevation = ButtonDefaults.buttonElevation(10.dp),
            colors = ButtonDefaults.buttonColors(
                Tertiary
            ),
            modifier = Modifier.size(50.dp)
        ){
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", Modifier.size(25.dp))
        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(percent = 10),
            elevation = ButtonDefaults.buttonElevation(10.dp),
            colors = ButtonDefaults.buttonColors(
                Tertiary
            ),
            modifier = Modifier.size(50.dp)
        ){
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", Modifier.size(25.dp))

        }
    }
}

@Preview
@Composable
fun ToolBarComponentPrev() {
    MaterialTheme {
        ToolBarComponent()
    }
}