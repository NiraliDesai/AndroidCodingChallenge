package com.example.otchallenge.di.module

import android.app.Application
import com.example.otchallenge.data.remote.ApiService
import com.example.otchallenge.data.remote.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Dagger module for providing network-related dependencies such as:
 * 1. to handle network API call.
 * 2. used retrofit to provide all services.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {

        // Configure logging interceptor for debug builds
        //todo if debug then we will pass this interceptor
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideBookApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}