package com.ltdt.coffeeshop_android_native.ui.screens.auth.register

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister
import com.ltdt.coffeeshop_android_native.data.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authService: AuthService,
) : ViewModel() {

    private val state = mutableIntStateOf(0) // 0 - Waiting; 1 - Loading; 2 - Success; 3 - Error
    val dialogState = state

    private val errorState = mutableStateOf("")
    val error = errorState

    val userFullName = mutableStateOf("")
    val userEmail = mutableStateOf("")
    val userPassword = mutableStateOf("")
    val userConfirmPassword = mutableStateOf("")
    val userPhoneNumber = mutableStateOf("")

    private fun registerFlow(user: UserRegister): Flow<Resource<String>> {
        return flow {
            try {
                emit(Resource.Loading())
                authService.register(user)
                emit(Resource.Success("Register successfully"))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    fun register() {
        if (userFullName.value.isEmpty() || userEmail.value.isEmpty() || userPassword.value.isEmpty() || userConfirmPassword.value.isEmpty() || userPhoneNumber.value.isEmpty()) {
            return
        }
        if (userPassword.value != userConfirmPassword.value) {

            return
        }
        val user = UserRegister(
            name = userFullName.value,
            email = userEmail.value,
            phoneNumber = userPhoneNumber.value,
            password = userPassword.value
        )

        viewModelScope.launch(Dispatchers.IO) {
            registerFlow(user).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        state.intValue = 2
                        userFullName.value = ""
                        userEmail.value = ""
                        userPassword.value = ""
                        userConfirmPassword.value = ""
                        userPhoneNumber.value = ""
                    }

                    is Resource.Loading -> {
                        state.intValue = 1
                    }

                    is Resource.Error -> {
                        state.intValue = 3
                        error.value = it.message ?: "An unexpected error"
                    }
                }
            }
        }
    }

}
