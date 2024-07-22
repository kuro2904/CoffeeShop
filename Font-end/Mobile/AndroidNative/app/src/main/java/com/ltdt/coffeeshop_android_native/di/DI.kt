package com.ltdt.coffeeshop_android_native.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.ltdt.coffeeshop_android_native.common.Constants.API_HOST
import com.ltdt.coffeeshop_android_native.data.repository.CategoryRepository
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import com.ltdt.coffeeshop_android_native.data.repository.impl.CategoryRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.repository.impl.ProductRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.services.ApiService
import com.ltdt.coffeeshop_android_native.data.services.AuthService
import com.ltdt.coffeeshop_android_native.data.services.JwtService
import com.ltdt.coffeeshop_android_native.data.services.SharePreferencesService
import com.ltdt.coffeeshop_android_native.data.services.impl.AuthServiceImpl
import com.ltdt.coffeeshop_android_native.data.services.impl.JwtServiceImpl
import com.ltdt.coffeeshop_android_native.data.services.impl.SharePreferencesServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://$API_HOST:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideAuthService(apiService: ApiService): AuthService {
        return AuthServiceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideJwtService(): JwtService{
        return JwtServiceImpl()
    }

    @Provides
    @Singleton
    fun provideSharePreferencesService(@ApplicationContext context: Context):SharePreferencesService{
        return SharePreferencesServiceImpl(context)
    }
}