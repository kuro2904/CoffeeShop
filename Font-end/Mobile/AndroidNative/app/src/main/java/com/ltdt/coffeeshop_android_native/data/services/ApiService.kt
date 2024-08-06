package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.domains.UserLogin
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister
import com.ltdt.coffeeshop_android_native.data.remote.CategoryDTO
import com.ltdt.coffeeshop_android_native.data.remote.OrderDTO
import com.ltdt.coffeeshop_android_native.data.remote.ProductDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("customers/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User

    @POST("auth/login")
    suspend fun login(@Body loginRequest: UserLogin): Token

    @POST("auth/signUp/customer")
    suspend fun register(@Body user: UserRegister): Token

    @GET("products")
    suspend fun getAllProducts(): List<ProductDTO>

    @GET("products/category")
    suspend fun getProductsByCategory(@Query("categoryId") categoryId: Int): List<ProductDTO>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: Int): ProductDTO

    @GET("categories")
    suspend fun getAllCategories(): List<CategoryDTO>

    @POST("orders/create")
    suspend fun placeOrder(@Body order: OrderDTO, @Header("Authorization") token: String): Boolean

    @GET("orders/{userId}")
    suspend fun getUserOrders(@Path("userId") userId: String): List<OrderDTO>


}