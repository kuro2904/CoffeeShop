package vn.ltdt.coffeeshop_android_native.data.repository.impl

import vn.ltdt.coffeeshop_android_native.data.domains.Product
import vn.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import vn.ltdt.coffeeshop_android_native.data.services.ApiService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
):ProductRepository {

    override suspend fun getAll(): List<Product> {
        return apiService.getAllProducts();
    }
}