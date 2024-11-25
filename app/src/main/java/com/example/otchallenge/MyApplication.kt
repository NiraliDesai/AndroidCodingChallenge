package com.example.otchallenge

import android.app.Application
import com.example.otchallenge.di.component.AppComponent
import com.example.otchallenge.di.component.DaggerAppComponent
import com.example.otchallenge.di.module.AppModule

/**
 * Application class for initializing Dagger components which is: AppComponent.kt
 */
class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // Application-wide Dagger component
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}