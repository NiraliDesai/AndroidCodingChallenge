package com.example.otchallenge.di.module

import com.example.otchallenge.di.scope.ActivityScope
import com.example.otchallenge.domain.usecases.GetBookListUseCase
import com.example.otchallenge.presenter.HomeContract
import com.example.otchallenge.presenter.HomePresenter
import com.example.otchallenge.util.network.NetworkUtils
import dagger.Module
import dagger.Provides

/**
 * Dagger module for providing presenter-related dependencies
 */
@Module
object PresenterModule {

    @Provides
    @ActivityScope
    fun provideHomePresenter(
        getBookListUseCase: GetBookListUseCase,
        networkUtils: NetworkUtils
    ): HomeContract.Presenter {
        return HomePresenter(getBookListUseCase, networkUtils)
    }
}
