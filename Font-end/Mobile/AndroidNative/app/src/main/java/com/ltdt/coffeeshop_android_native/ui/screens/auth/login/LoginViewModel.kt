package com.ltdt.coffeeshop_android_native.ui.screens.auth.login

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.UserLogin
import com.ltdt.coffeeshop_android_native.data.services.AuthService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
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
class LoginViewModel @Inject constructor(
    private val authService: AuthService,
    private val dataStorePref: SharePreferencesService
) : ViewModel() {

    private var _state = mutableIntStateOf(0) // 0 - Waiting; 1 - Loading; 2 - Success; 3 - Error
    val state = _state

    private val errorState = mutableStateOf("")
    val error = errorState

    var emailState = mutableStateOf("")
    var passwordState = mutableStateOf("")

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loginFlow(email, password).collectLatest {
                when (it) {
                    is Resource.Success -> {
                        _state.intValue = 2
                        emailState.value = ""
                        passwordState.value = ""
                        it.data?.let { token ->
                            dataStorePref.saveStringKey("token", token.token)
                            Log.e(
                                "Token Preferences",
                                dataStorePref.getStringKey("token").toString()
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _state.intValue = 1
                    }

                    is Resource.Error -> {
                        _state.intValue = 3
                        error.value = it.message ?: "An unexpected error"
                    }
                }
            }
        }


    }

    private fun loginFlow(email: String, password: String): Flow<Resource<Token>> {
        return flow {
            try {
                emit(Resource.Loading())
                emit(Resource.Success(authService.login(UserLogin(email, password))))
            } catch (e: HttpException) {
                emit(Resource.Error(e.message ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }
}