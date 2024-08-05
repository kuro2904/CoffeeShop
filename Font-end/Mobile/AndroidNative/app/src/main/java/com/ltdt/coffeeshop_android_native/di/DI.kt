package com.ltdt.coffeeshop_android_native.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import com.ltdt.coffeeshop_android_native.common.Constants.API_HOST
import com.ltdt.coffeeshop_android_native.data.repository.CartRepository
import com.ltdt.coffeeshop_android_native.data.repository.CategoryRepository
import com.ltdt.coffeeshop_android_native.data.repository.OrderRepository
import com.ltdt.coffeeshop_android_native.data.repository.ProductRepository
import com.ltdt.coffeeshop_android_native.data.repository.UserRepository
import com.ltdt.coffeeshop_android_native.data.repository.impl.CartRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.repository.impl.CategoryRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.repository.impl.OrderRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.repository.impl.ProductRepositoryImpl
import com.ltdt.coffeeshop_android_native.data.repository.impl.UserRepositoryImpl
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
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Provides
    @Singleton
    fun provideApiService(gson: Gson): ApiService {

        return Retrofit.Builder()
            .baseUrl("http://$API_HOST:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductRepository(apiService: ApiService): ProductRepository {
        return ProductRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        val formatter = DateTimeFormatter.ofPattern(dateFormat)
        val gson: Gson = GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonDeserializer { json, _, _ ->
                    ZonedDateTime.parse(json.asJsonPrimitive.asString).toLocalDateTime()
                } as JsonDeserializer<LocalDateTime?>
            )
            .registerTypeAdapter(
                LocalDateTime::class.java,
                JsonSerializer<LocalDateTime?> { localDate, _, _ ->
                    JsonPrimitive(formatter.format(localDate))
                }
            )
            .create()
        return gson
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(apiService: ApiService): CategoryRepository {
        return CategoryRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideOrderRepository(apiService: ApiService): OrderRepository {
        return OrderRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserRepository(apiService: ApiService): UserRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideAuthService(apiService: ApiService): AuthService {
        return AuthServiceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideJwtService(sharePreferencesService: SharePreferencesService): JwtService {
        return JwtServiceImpl(sharePreferencesService)
    }


    @Provides
    @Singleton
    fun provideSharePreferencesService(@ApplicationContext context: Context): SharePreferencesService {
        return SharePreferencesServiceImpl(context)
    }

}