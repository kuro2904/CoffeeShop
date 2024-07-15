package com.ltdt.coffeeshop_android_native.ui.screens.auth.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var phoneNumber = mutableStateOf("")
    var password = mutableStateOf("")

    fun login(phoneNumber: String, password: String) {

    }

    fun loginWithGoogle() {

    }


    fun navigateToSignUp() {
        Log.i("LoginViewModel", "navigateToSignUp")
    }


}