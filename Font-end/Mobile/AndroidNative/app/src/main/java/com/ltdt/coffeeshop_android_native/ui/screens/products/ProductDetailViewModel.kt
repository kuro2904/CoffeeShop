package com.ltdt.coffeeshop_android_native.ui.screens.products

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.IOException
import javax.inject.Inject

data class ProductState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val productId: Int = checkNotNull(savedStateHandle["productId"])
    private val _productState = mutableStateOf(ProductState())
    val productState: State<ProductState> = _productState

    init {
        getProduct(productId)
    }

    private fun getProductFlow(productId: Int): Flow<Resource<Product>> {
        return flow {
            try {
                emit(Resource.Loading())
                val product = productRepository.getProductById(productId)
                emit(Resource.Success(product))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    private fun getProduct(productId: Int) {
        getProductFlow(productId).onEach {
            when (it) {
                is Resource.Loading -> _productState.value = ProductState(isLoading = true)
                is Resource.Success -> {
                    _productState.value = ProductState(product = it.data)
                    Log.i("Product Infor", it.data.toString())
                }

                is Resource.Error -> _productState.value =
                    ProductState(error = it.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }

}