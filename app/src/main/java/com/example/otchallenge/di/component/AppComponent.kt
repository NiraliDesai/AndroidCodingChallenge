package com.example.otchallenge.di.component

import android.app.Application
import com.example.otchallenge.di.module.AppModule
import com.example.otchallenge.di.module.NetworkModule
import com.example.otchallenge.domain.repository.BooksRepository
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Dagger component for providing application-wide dependencies
 * also, this contains list of modules such as : AppModule, NetworkModule.
 */

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun provideBooksRepository(): BooksRepository
    fun provideApplication(): Application
    fun provideRetrofit(): Retrofit

    // Factory for creating PresenterComponent
    fun presenterComponent(): PresenterComponent.Factory
}