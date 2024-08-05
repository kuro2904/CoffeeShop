package com.ltdt.coffeeshop_android_native.ui.screens.order_histories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Order
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderHistoryViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val sharePreferencesService: SharePreferencesService,
    private val jwtService: JwtService
) : ViewModel() {

    private val _state = mutableIntStateOf(0)
    val state: State<Int> = _state

    private val _orderHistories = mutableStateOf(emptyList<Order>())
    val orderHistories: State<List<Order>> = _orderHistories

    init {
        viewModelScope.launch(Dispatchers.IO) {
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
                emit(Resource.Success(orders))
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "Unknown error"))
            }
        }
    }

    private fun getOrders(userId: String) {
        getOrderFlow(userId).onEach {
            when (it) {
                is Resource.Success -> {
                    _orderHistories.value = (it.data ?: emptyList())
                    _state.intValue = 1
                }

                is Resource.Error -> {
                    _state.intValue = -1
                }

                is Resource.Loading -> {
                    _state.intValue = 0
                }
            }
        }
    }
}