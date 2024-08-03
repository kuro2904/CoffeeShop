package com.ltdt.coffeeshop_android_native.ui.screens.payments

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ltdt.coffeeshop_android_native.R
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.DeliveryType
import com.ltdt.coffeeshop_android_native.data.domains.PaymentType
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.repository.CartRepository
import com.ltdt.coffeeshop_android_native.data.repository.UserRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import com.ltdt.coffeeshop_android_native.ui.screens.payments.components.PaymentMethodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject


@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository,
    private val sharePreferencesService: SharePreferencesService,
    private val jwtService: JwtService
) : ViewModel() {

    val paymentMethodList = listOf(
        PaymentMethodItem(PaymentType.COD, R.drawable.ic_cod),
        PaymentMethodItem(PaymentType.MOMO, R.drawable.logo_momo),
        PaymentMethodItem(PaymentType.VNPAY, R.drawable.vnpay_logo),
    )

    private var _state = mutableIntStateOf(0)
    val state = _state

    private var _deliveryType = mutableStateOf(DeliveryType.DELIVERY)
    val deliveryType: State<DeliveryType> = _deliveryType

    private var _orders = mutableStateOf(cartRepository.getCartItems())
    val orders = _orders

    private var _note = mutableStateOf("")
    val note: State<String> = _note

    private var _paymentMethod = mutableStateOf(paymentMethodList[0].title)
    val paymentMethod: State<PaymentType> = _paymentMethod

    private var _user = mutableStateOf<User?>(null)
    val user: State<User?> = _user

    private var _modalSheetState = mutableStateOf(ModalState())
    val modalSheetState: State<ModalState> = _modalSheetState

    private var _orderDate = mutableStateOf(LocalDateTime.now().plusMinutes(30))
    val orderDate: State<LocalDateTime> = _orderDate

    init {
        getUser()
    }

    private fun getUserFlow(userId: String): Flow<Resource<User>> {
        return flow {
            try {
                emit(Resource.Loading())
                val user = userRepository.getUser(userId)
                emit(Resource.Success(user))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            }
        }
    }

    private fun getUser() {
        viewModelScope.launch {
            val token = sharePreferencesService.getStringKey("token")
            if (token != null) {
                val userId = jwtService.getUserId(token)
                getUserFlow(userId).collect {
                    when (it) {
                        is Resource.Success -> {
                            _state.intValue = 1
                            _user.value = it.data
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
    }

    fun getTotalPrice(): Float {
        return cartRepository.getTotalPrice()
    }

    fun changeDeliType() {
        if (_deliveryType.value == DeliveryType.DELIVERY) {
            _deliveryType.value = DeliveryType.TAKE_AWAY
        } else {
            _deliveryType.value = DeliveryType.DELIVERY
        }
    }

    fun onNoteChange(note: String) {
        _note.value = note
    }


    // action: 1 -> change delivery address
    // action: 2 -> change user info
    // action: 3 -> change delivery time
    // action: 4 -> change payment method
    // action: 5 -> change pick up address
    fun openModalState(action: Int) {
        _modalSheetState.value = _modalSheetState.value.copy(isShow = true)
        _modalSheetState.value = _modalSheetState.value.copy(action = action)
    }

    fun closeModalState() {
        _modalSheetState.value = _modalSheetState.value.copy(isShow = false)
    }

    fun changePaymentMethod(paymentType: PaymentType) {
        _paymentMethod.value = paymentType
    }

    fun changeUserInfo(name: String, phoneNumber: String) {
        _user.value = _user.value?.copy(name = name)
        _user.value = _user.value?.copy(phoneNumber = phoneNumber)
    }

}

data class ModalState(
    var action: Int = 1,
    var isShow: Boolean = false
)
