package com.example.otchallenge.presenter

import com.example.otchallenge.domain.model.BookModel
import com.example.otchallenge.presenter.base.BaseContract

/**
 * HomeContract interface defining the view and presenter for the home screen.
 * As per (Model-View-Presenter) architecture, this defines the contracts of the screen for retrieving and displaying the list of books.
 */

interface HomeContract {

    //specifies the UI updates on the screen.
    interface View : BaseContract.View {
        fun showLoading()
        fun hideLoading()
        fun showData(data: List<BookModel>)
        fun showError(error: String)
    }

    //specifies the actions based on user interaction events.
    interface Presenter : BaseContract.Presenter<View> {
        fun fetchBookData()
    }
}