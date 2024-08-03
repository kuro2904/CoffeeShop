package com.ltdt.coffeeshop_android_native.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.repository.UserRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sharePreferencesService: SharePreferencesService,
    private val jwtService: JwtService
) : ViewModel() {

    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private val _selectedIndex = mutableIntStateOf(0)
    val selectedIndex = _selectedIndex

    fun updateSelectedIndex(index: Int) {
        _selectedIndex.intValue = index
    }

    init {
        viewModelScope.launch {
            val token = sharePreferencesService.getStringKey("token")
            if (token != null) {
                _user.value = userRepository.getUser(jwtService.getUserId(token))
            }
        }
    }

}