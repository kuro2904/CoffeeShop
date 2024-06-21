package vn.ltdt.coffeeshop_android_native.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vn.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import vn.ltdt.coffeeshop_android_native.data.repository.impl.ProductRepositoryImpl
import vn.ltdt.coffeeshop_android_native.data.services.ApiService
import vn.ltdt.coffeeshop_android_native.di.Constants.API_HOST
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideApiService():ApiService{
        return Retrofit.Builder()
            .baseUrl("http://$API_HOST:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService):ProductRepository{
        return ProductRepositoryImpl(apiService)
    }

}