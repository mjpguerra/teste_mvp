package com.example.lojong.presentation.insight.quotes

import android.graphics.Bitmap
import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView
import com.example.lojong.repository.remote.insight.model.Quote

interface QuotesContract {
    interface View : BaseView<Presenter> {
        fun setViews()
        fun showLoading()
        fun hideLoading()
        fun updateAdapter(items: List<Quote>)
        fun onShareClick(bitmap: Bitmap?, position: Int)
        fun onLoadQuotesSuccessful(items: List<Quote>)
        fun onLoadQuotesError()
        fun onShareQuoteError()
        fun handleItemsVisibility(shouldShowItems: Boolean)
    }
    interface Presenter : BasePresenter<View> {
        fun init()
        fun loadQuotes()
        fun onLoadQuotesSuccessful(items: List<Quote>)
        fun onLoadQuotesError()
    }
}