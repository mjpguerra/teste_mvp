package com.example.lojong.presentation.fundamentals

import androidx.lifecycle.MutableLiveData
import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView
import com.example.lojong.repository.local.fundamentals.ExampleData

interface FundamentalsContract {
    interface View : BaseView<Presenter> {
        fun initPresenter()
        fun observeData()
        fun onReload()
        fun handleMessageVisibility(shouldShow: Boolean)
        fun handleLoadingVisibility(shouldShow: Boolean)
    }
    interface Presenter : BasePresenter<View> {
        fun init()
        fun loadData()
        fun observeForExampleData(): MutableLiveData<ExampleData>
    }
}