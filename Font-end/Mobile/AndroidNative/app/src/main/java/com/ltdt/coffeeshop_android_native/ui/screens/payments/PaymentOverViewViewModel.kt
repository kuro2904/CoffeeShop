package com.ltdt.coffeeshop_android_native.ui.screens.payments

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Order
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
    private val jwtService: JwtService
) : ViewModel() {

    private var _state = mutableIntStateOf(0)
    val state = _state

    private var _order = mutableStateOf<Order?>(null)
    val order: State<Order?> = _order

    init {
        val orderString = savedStateHandle.get<String>("order")
        _order.value = gson.fromJson(orderString, Order::class.java)
    }

    private fun placeOrderFlow(order: Order, token: String): Flow<Resource<Order>> {
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
                val oderTemp = _order.value?.copy(customerId = jwtService.getUserId(token))
                if (oderTemp != null) {
                    Log.e("Result", "OderTemp + $oderTemp")
                    placeOrderFlow(order = oderTemp, token = token).collect {
                        when (it) {
                            is Resource.Success -> {
                                _state.intValue = 1
                            }

                            is Resource.Loading -> {
                                _state.intValue = 0
                            }

                            is Resource.Error -> {
                                _state.intValue = -1
                                Log.e("Result", "State + ${it.message}")

                            }
                        }
                    }
                } else {
                    _state.intValue = -1
                }

            } else {
                _state.intValue = -1
            }
        }
    }
}

