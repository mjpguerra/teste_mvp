package com.example.lojong.presentation.insight.articles

import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView
import com.example.lojong.repository.remote.insight.model.Article

interface ArticlesContract {
    interface View : BaseView<Presenter> {
        fun setViews()
        fun showLoading()
        fun hideLoading()
        fun updateAdapter(items: List<Article>)
        fun onShareClick(articleItem: Article, position: Int)
        fun onLoadArticlesSuccessful(items: List<Article>)
        fun onLoadArticlesError()
        fun handleItemsVisibility(shouldShowItems: Boolean)
    }
    interface Presenter : BasePresenter<View> {
        fun init()
        fun loadArticles()
        fun onLoadArticlesSuccessful(items: List<Article>)
        fun onLoadArticlesError()
    }
}