package vn.ltdt.coffeeshop_android_native.data.services

import retrofit2.http.GET
import vn.ltdt.coffeeshop_android_native.data.domains.Product

interface ApiService {

    @GET("products")
    suspend fun getAllProducts():List<Product>
}