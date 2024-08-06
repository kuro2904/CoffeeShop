package com.ltdt.coffeeshop_android_native.ui.screens.payments

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.repository.CartRepository
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentOverViewViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val sharePreferencesService: SharePreferencesService,
    gson: Gson,
    private val orderRepository: OrderRepository,
    private val jwtService: JwtService,
    private val cartRepository: CartRepository
) : ViewModel() {

    private var _state = mutableStateOf(PaymentOverViewState())
    val state: State<PaymentOverViewState> = _state


    init {
        val orderString = savedStateHandle.get<String>("order")
        _state.value = _state.value.copy(order = gson.fromJson(orderString, Order::class.java))
    }

    private fun placeOrderFlow(order: Order, token: String): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading())
            try {
                emit(Resource.Success(orderRepository.placeOrder(order, "Bearer $token")))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An unexpected error"))
            }
        }
    }

    fun placeOrder() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = sharePreferencesService.getStringKey("token")
            if (token != null) {
                // Call the orderRepository to place the order
                val oderTemp = _state.value.order?.copy(customerId = jwtService.getUserId(token))
                if (oderTemp != null) {
                    Log.e("Result", "OderTemp + $oderTemp")
                    placeOrderFlow(order = oderTemp, token = token).collect {
                        when (it) {
                            is Resource.Success -> {
                                Log.e("Result", "${it.data}")
                                if (it.data == true) {
                                    cartRepository.clearCart()
                                }
                                _state.value = _state.value.copy(state = 1)
                            }

                            is Resource.Loading -> {
                                _state.value = _state.value.copy(state = 0)
                            }

                            is Resource.Error -> {
                                _state.value = _state.value.copy(state = -1)
                                Log.e("Result", "State + ${it.message}")
                            }
                        }
                    }
                } else {
                    _state.value = _state.value.copy(state = -1)
                }

            } else {
                _state.value = _state.value.copy(state = -1)
            }
        }
    }
}

data class PaymentOverViewState(
    val order: Order? = null,
    val state: Int = 0
)
