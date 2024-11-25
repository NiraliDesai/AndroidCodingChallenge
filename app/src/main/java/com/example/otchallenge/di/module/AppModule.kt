package com.example.otchallenge.di.module

import android.app.Application
import android.content.Context
import com.example.otchallenge.data.remote.ApiService
import com.example.otchallenge.data.repository.BooksRepositoryImpl
import com.example.otchallenge.domain.repository.BooksRepository
import com.example.otchallenge.util.network.NetworkUtils
import com.example.otchallenge.util.network.NetworkUtilsImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger module for providing application-level dependencies
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideBooksRepository(apiService: ApiService): BooksRepository {
        return BooksRepositoryImpl(apiService)
    }

    @Provides
    fun provideNetworkUtils(context: Context): NetworkUtils {
        return NetworkUtilsImpl(context)
    }

}