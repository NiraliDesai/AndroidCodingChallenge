package com.example.otchallenge.di.component

import com.example.otchallenge.di.module.PresenterModule
import com.example.otchallenge.di.scope.ActivityScope
import com.example.otchallenge.presenter.ui.HomeActivity
import dagger.Subcomponent

/**
 * Dagger subcomponent for providing presenter-specific dependencies
 */
@ActivityScope
@Subcomponent(modules = [PresenterModule::class])
interface PresenterComponent {

    // Inject into the target Activity
    fun inject(activity: HomeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): PresenterComponent
    }
}