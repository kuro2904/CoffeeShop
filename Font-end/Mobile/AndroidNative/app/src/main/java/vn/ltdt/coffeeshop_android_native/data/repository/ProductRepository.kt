package vn.ltdt.coffeeshop_android_native.data.repository

import vn.ltdt.coffeeshop_android_native.data.domains.Product

interface ProductRepository {
    suspend fun getAll():List<Product>
}