package com.example.lojong.presentation.insight.quotes

import com.example.lojong.base.extensions.launchIO
import com.example.lojong.base.extensions.launchMain
import com.example.lojong.repository.Repository
import com.example.lojong.repository.remote.insight.model.Quote
import com.example.lojong.repository.remote.insight.responses.whenever

class QuotesPresenter(
    override var view: QuotesContract.View,
    private val repository: Repository
) : QuotesContract.Presenter {

    override fun init() {}

    override fun loadQuotes() {
        view.showLoading()
        launchIO {
            repository.remote.insightResources.getQuotes().whenever(
                isBody = { onLoadQuotesSuccessful(it) },
                isError = { onLoadQuotesError() },
                isEmptyBody = { onLoadQuotesError() },
                isOk = { onLoadQuotesError() }
            )
        }
    }

    override fun onLoadQuotesSuccessful(items: List<Quote>) {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = items.isNotEmpty())
            view.onLoadQuotesSuccessful(items)
            view.hideLoading()
        }
    }

    override fun onLoadQuotesError() {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = false)
            view.onLoadQuotesError()
            view.hideLoading()
        }
    }
}