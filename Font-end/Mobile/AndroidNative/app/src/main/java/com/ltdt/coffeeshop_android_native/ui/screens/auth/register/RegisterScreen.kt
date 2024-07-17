package com.ltdt.coffeeshop_android_native.ui.screens.auth.register

import AlertDialog
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.ui.navigations.Screen
import com.ltdt.coffeeshop_android_native.ui.theme.Gray
import com.ltdt.coffeeshop_android_native.ui.theme.Primary

@Composable
fun RegisterScreen(
    modifier: Modifier,
    navController: NavController,
    context: Activity,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
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
                containerColor = Gray, contentColor = Color.White
            )
        ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Button Close")
        }
        Card(
            modifier = Modifier.align(Alignment.BottomCenter), colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), shape = RoundedCornerShape(
                topStartPercent = 5, topEndPercent = 5
            )
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Welcome to",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    textAlign = TextAlign.Center, fontSize = 18.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Coffee Shop",
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    textAlign = TextAlign.Center, fontSize = 30.sp
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Full name") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        value = viewModel.userFullName.value,
                        onValueChange = { viewModel.userFullName.value = it })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Email") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        value = viewModel.userEmail.value,
                        onValueChange = { viewModel.userEmail.value = it })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Phone Number") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        value = viewModel.userPhoneNumber.value,
                        onValueChange = { viewModel.userPhoneNumber.value = it })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Password") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        value = viewModel.userPassword.value,
                        onValueChange = { viewModel.userPassword.value = it })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text(text = "Confirm password") },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                        ),
                        value = viewModel.userConfirmPassword.value,
                        onValueChange = { viewModel.userConfirmPassword.value = it })

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Back to login?",
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.popBackStack() },
                        textAlign = TextAlign.End,
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    ElevatedButton(
                        onClick = { viewModel.register() },
                        shape = RoundedCornerShape(percent = 15),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Primary
                        )
                    ) {
                        Log.d("TAG", "RegisterScreen: ${viewModel.dialogState.intValue}")
                        when (viewModel.dialogState.intValue) {
                            0 -> {
                                Text(
                                    text = "Register",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                            }

                            1 -> {
                                CircularProgressIndicator()
                            }

                            2 -> {
                                context.finish()
                            }

                            3 -> {
                                Text(
                                    text = "Error, Try Again",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color.White
                                )
                                AlertDialog(
                                    onDismissRequest = {
                                        viewModel.dialogState.intValue = 0
                                    },
                                    onConfirmation = {
                                        viewModel.dialogState.intValue = 0
                                    },
                                    dialogTitle = "Error",
                                    dialogText = viewModel.error.value,
                                    icon = Icons.Default.Warning
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}