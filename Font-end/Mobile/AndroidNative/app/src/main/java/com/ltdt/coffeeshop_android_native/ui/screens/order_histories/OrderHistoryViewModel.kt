package com.ltdt.coffeeshop_android_native.ui.screens.order_histories

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.remote.toEntity
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sharePreferencesService: SharePreferencesService,
    private val jwtService: JwtService
) : ViewModel() {

    private val _state = mutableStateOf(OrderHistoryState())
    val state: State<OrderHistoryState> = _state

    init {
        viewModelScope.launch {
            val token = sharePreferencesService.getStringKey("token")
            if (token != null) {
                getOrders(jwtService.getUserId(token))
            }
        }
    }

    private fun getOrderFlow(userId: String): Flow<Resource<List<Order>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val orders = orderRepository.getUserOrders(userId)
                emit(Resource.Success(orders.map { it.toEntity() }))
                Log.d("OrderHistoryViewModel", "getOrderFlow: $orders")
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }

    private fun getOrders(userId: String) {
        getOrderFlow(userId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        orderHistories = it.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = it.message.toString(),
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    _state.value = _state.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

data class OrderHistoryState(
    val isLoading: Boolean = false,
    val orderHistories: List<Order> = emptyList(),
    val error: String = ""
)
