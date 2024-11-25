package com.example.otchallenge.presenter.base

interface BaseContract {

    interface Presenter<V : View> {
        fun attachView(view: V)
        fun onDestroy()
    }

    interface View
}