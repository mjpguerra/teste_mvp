package com.example.lojong.presentation.insight.articles

import com.example.lojong.base.extensions.launchIO
import com.example.lojong.base.extensions.launchMain
import com.example.lojong.repository.Repository
import com.example.lojong.repository.remote.insight.model.Article
import com.example.lojong.repository.remote.insight.responses.whenever

class ArticlesPresenter(
    override var view: ArticlesContract.View,
    private val repository: Repository
) : ArticlesContract.Presenter {

    override fun init() {}

    override fun loadArticles() {
        view.showLoading()
        launchIO {
            repository.remote.insightResources.getArticles().whenever(
                isBody = { onLoadArticlesSuccessful(it.articles) },
                isError = { onLoadArticlesError() },
                isEmptyBody = { onLoadArticlesError() },
                isOk = { onLoadArticlesError() }
            )
        }
    }

    override fun onLoadArticlesSuccessful(items: List<Article>) {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = items.isNotEmpty())
            view.onLoadArticlesSuccessful(items)
            view.hideLoading()
        }
    }

    override fun onLoadArticlesError() {
        launchMain {
            view.handleItemsVisibility(shouldShowItems = false)
            view.onLoadArticlesError()
            view.hideLoading()
        }
    }
}