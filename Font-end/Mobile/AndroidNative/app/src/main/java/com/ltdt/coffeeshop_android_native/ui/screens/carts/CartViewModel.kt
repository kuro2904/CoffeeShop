package com.ltdt.coffeeshop_android_native.ui.screens.carts

import androidx.lifecycle.ViewModel
import com.ltdt.coffeeshop_android_native.data.domains.OrderDetail
import com.ltdt.coffeeshop_android_native.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
) : ViewModel() {

    private val _cartItems = MutableStateFlow(cartRepository.getCartItems())
    val cartItems: StateFlow<List<OrderDetail>> = _cartItems

    fun increaseQuantity(orderDetail: OrderDetail) {
        val updatedItems = _cartItems.value.map {
            if (it == orderDetail) {
                val newQuantity = it.copy(quantity = it.quantity + 1)
                cartRepository.updateQuantity(newQuantity, newQuantity.quantity)
                newQuantity
            } else it
        }
        _cartItems.value = updatedItems
    }

    fun decreaseQuantity(orderDetail: OrderDetail) {
        val updatedItems = _cartItems.value.map {
            if (it == orderDetail && it.quantity > 1) {
                val newQuantity = it.copy(quantity = it.quantity - 1)
                cartRepository.updateQuantity(newQuantity, newQuantity.quantity)
                newQuantity
            } else it
        }
        _cartItems.value = updatedItems
    }

    fun getTotalPrice(): Double {
        return cartRepository.getTotalPrice()
    }

}
