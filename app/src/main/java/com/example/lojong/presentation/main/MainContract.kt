package com.example.lojong.presentation.main

import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showLoading()
        fun hideLoading()
    }
    interface Presenter : BasePresenter<View> {
        fun init()
    }
}