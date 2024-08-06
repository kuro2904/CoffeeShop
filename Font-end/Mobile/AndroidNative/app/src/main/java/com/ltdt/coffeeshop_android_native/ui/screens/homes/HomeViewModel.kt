package com.ltdt.coffeeshop_android_native.ui.screens.homes

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.ltdt.coffeeshop_android_native.common.Resource
import com.ltdt.coffeeshop_android_native.data.domains.Category
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.remote.toEntity
import com.ltdt.coffeeshop_android_native.data.repository.CategoryRepository
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

data class ProductListState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String = ""
)

data class CategoryListState(
    val isLoading: Boolean = false,
    var categories: List<Category> = emptyList(),
    val error: String = ""
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {


    private val _productListState = mutableStateOf(ProductListState())
    val productsState: State<ProductListState> = _productListState

    private val _categoryListState = mutableStateOf(CategoryListState())
    val categoriesState: State<CategoryListState> = _categoryListState

    var searchInputText = mutableStateOf(TextFieldValue(""))
    var tabSelectedIndex = mutableIntStateOf(0)

    init {
        getProducts()
        getCategories()
    }


    private fun getProductsFlow(): Flow<Resource<List<Product>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val products = productRepository.getAll()
                emit(Resource.Success(products.map { it.toEntity() }))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    private fun getProductsByCategoryFlow(categoryId: Int): Flow<Resource<List<Product>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val filteredProducts = productRepository.getProductsByCategory(categoryId)
                emit(Resource.Success(filteredProducts.map { it.toEntity() }))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }


    private fun getCategoriesFlow(): Flow<Resource<List<Category>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val products = categoryRepository.getAll()
                emit(Resource.Success(products.map { it.toEntity() }))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }


    private fun getProducts() {
        getProductsFlow().onEach { result ->
            when (result) {
                is Resource.Success -> _productListState.value =
                    ProductListState(products = result.data ?: emptyList())

                is Resource.Loading -> _productListState.value = ProductListState(isLoading = true)
                is Resource.Error -> _productListState.value =
                    ProductListState(error = result.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }

    private fun getFilteredProducts(categoryId: Int) {
        getProductsByCategoryFlow(categoryId).onEach { result ->
            when (result) {
                is Resource.Success -> _productListState.value =
                    ProductListState(products = result.data ?: emptyList())

                is Resource.Loading -> _productListState.value = ProductListState(isLoading = true)
                is Resource.Error -> _productListState.value =
                    ProductListState(error = result.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        getCategoriesFlow().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _categoryListState.value =
                        CategoryListState(categories = result.data ?: emptyList())
                    Log.i("category fetch", result.data.toString())
                }

                is Resource.Error -> _categoryListState.value =
                    CategoryListState(error = result.message ?: "An unexpected error occured")

                is Resource.Loading -> _categoryListState.value =
                    CategoryListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    fun productFilteredByCategory(categoryId: Int) {
        viewModelScope.launch {
            if (categoryId == 0) {
                getProducts()
            } else {
                getFilteredProducts(categoryId)
            }
        }
    }

}



