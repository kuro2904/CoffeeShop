package com.ltdt.coffeeshop_android_native.data.services

import com.ltdt.coffeeshop_android_native.data.domains.Category
import com.ltdt.coffeeshop_android_native.data.domains.Product
import com.ltdt.coffeeshop_android_native.data.domains.Token
import com.ltdt.coffeeshop_android_native.data.domains.User
import com.ltdt.coffeeshop_android_native.data.domains.UserLogin
import com.ltdt.coffeeshop_android_native.data.domains.UserRegister
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: UserLogin): Token

    @POST("auth/signUp/customer")
    suspend fun register(@Body user: UserRegister): Token

    @GET("products")
    suspend fun getAllProducts(): List<Product>

    @GET("products/category")
    suspend fun getProductsByCategory(@Query("categoryId") categoryId: Int): List<Product>

    @GET("products/{productId}")
    suspend fun getProductById(@Path("productId") productId: Int): Product

    @GET("categories")
    suspend fun getAllCategories(): List<Category>


}