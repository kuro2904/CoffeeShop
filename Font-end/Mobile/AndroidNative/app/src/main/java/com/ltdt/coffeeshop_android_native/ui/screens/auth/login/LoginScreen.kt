package com.ltdt.coffeeshop_android_native.ui.screens.auth.login

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.navigations.Screen
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
    context: Activity
) {

    Box(modifier = modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.im_bg),
            contentDescription = "Image background",
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.TopCenter,
        )
        IconButton(
            onClick = { context.finish() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Gray,
                contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(
                topStartPercent = 5,
                topEndPercent = 5
            )

        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Welcome to",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontSize = 18.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Coffee Shop",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    textAlign = TextAlign.Center,
                                    fontSize = 30.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Email") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.Black
                        ),
                        value = viewModel.emailState.value,
                        onValueChange = { viewModel.emailState.value = it })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color.Black
                        ),
                        value = viewModel.passwordState.value,
                        onValueChange = { viewModel.passwordState.value = it })

                    Spacer(modifier = Modifier.height(20.dp))

                    ElevatedButton(
                        onClick = {
                            viewModel.login(
                                email = viewModel.emailState.value,
                                password = viewModel.passwordState.value
                            )
                            if(viewModel.dialogState.intValue == 2) context.finish()
                        },
                        shape = RoundedCornerShape(percent = 15),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        )
                    ) {
                        Text(text = "Login")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Don't have an account? Create one",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.RegisterScreen.route) },
                        textAlign = TextAlign.End,
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "Or",
                            modifier = Modifier.padding(horizontal = 10.dp),
                        )
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier.padding(bottom = 50.dp)) {
                        ElevatedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(percent = 15),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            ),
                            border = ButtonDefaults.outlinedButtonBorder,
                            enabled = true
                        ) {
                            Row(
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_google),
                                    contentDescription = "Google Icon",
                                    modifier = Modifier.size(25.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(text = "Login with Google")
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun LoginScreenPrev() {
    MaterialTheme {
//        LoginScreen(navController = NavController(LocalContext.current))
    }


}