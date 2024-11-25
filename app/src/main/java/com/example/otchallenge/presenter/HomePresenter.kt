package com.example.otchallenge.presenter

import com.example.otchallenge.domain.usecases.GetBookListUseCase
import com.example.otchallenge.util.network.NetworkUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Presenter class for the home screen, responsible for handling the business logic
 * This will interact with HomeActivity and network API call to fetch the book data from API.
 * - Handles Data retrieval and it's error
 * - Handles buttons action for:  button Retry.
 */

class HomePresenter @Inject constructor(
    private val fetchDataUseCase: GetBookListUseCase,
    private val networkUtils: NetworkUtils
) : HomeContract.Presenter {

    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    private var view: HomeContract.View? = null


    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun fetchBookData() {

        if (!networkUtils.isNetworkAvailable()) {
            view?.hideLoading()
            //TODo - Instead of hardcoded error message, we can do it this from string.xml
            view?.showError("No internet connection. Please try again.")
            return
        }

        view?.showLoading()


        scope.launch {
            try {
                val result = fetchDataUseCase()
                result.onSuccess { data ->
                    view?.showData(data)
                }.onFailure { error ->
                    //TODo - Instead of hardcoded error message, we can do it this from string.xml
                    view?.showError(error.message ?: "Something went wrong. \n Please try again.")
                }
            } catch (e: Exception) {
                view?.showError(
                    e.message ?: ("Something went wrong. \n" +
                            " Please try again.")
                )
            } finally {
                view?.hideLoading()  // Ensure hideLoading is always called
            }
        }
    }

    override fun onDestroy() {
        view = null
        job.cancel()
    }
}

