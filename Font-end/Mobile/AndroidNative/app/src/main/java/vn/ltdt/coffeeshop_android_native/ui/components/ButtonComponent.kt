package vn.ltdt.coffeeshop_android_native.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun ButtonComponent(
    icon: Painter?, // Use Painter for the image
    text: String?,
    width: Int,
    height: Int,
    onClick: () -> Unit
) {
    Spacer(modifier = Modifier.height(5.dp))
    Button(
        onClick = onClick,
        modifier = Modifier.size(width = width.dp, height = height.dp),
        shape = RoundedCornerShape(
            percent = 10
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White
        ),
        content = {
            if (icon != null) {
                Image(painter = icon, contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (!text.isNullOrBlank()) {
                Text(text, fontSize = 40.sp)
            }
        }
    )
}