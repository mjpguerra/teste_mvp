package com.example.lojong.base.di

import com.example.lojong.base.application.ReleaseStartApplication
import com.example.lojong.base.application.StarterApplication
import com.example.lojong.presentation.fundamentals.FundamentalsContract
import com.example.lojong.presentation.fundamentals.FundamentalsPresenter
import com.example.lojong.presentation.insight.articles.ArticlesContract
import com.example.lojong.presentation.insight.articles.ArticlesPresenter
import com.example.lojong.presentation.insight.quotes.QuotesContract
import com.example.lojong.presentation.insight.quotes.QuotesPresenter
import com.example.lojong.presentation.insight.videos.VideosContract
import com.example.lojong.presentation.insight.videos.VideosPresenter
import com.example.lojong.presentation.main.MainContract
import com.example.lojong.presentation.main.MainPresenter
import com.example.lojong.repository.local.fundamentals.ExampleRepositoryMock
import org.koin.dsl.bind
import org.koin.dsl.module

object PresenterModule {


    val modules = module {

        factory { ReleaseStartApplication() } bind StarterApplication::class
        factory { ExampleRepositoryMock() } bind ExampleRepositoryMock::class

        factory { (view: MainContract.View) -> MainPresenter(view = view) } bind MainContract.Presenter::class
        factory { (view: FundamentalsContract.View) -> FundamentalsPresenter(view = view, repository = get()) } bind FundamentalsContract.Presenter::class
        factory { (view: VideosContract.View) -> VideosPresenter(view = view, repository = get()) } bind VideosContract.Presenter::class
        factory { (view: ArticlesContract.View) -> ArticlesPresenter(view = view, repository = get()) } bind ArticlesContract.Presenter::class
        factory { (view: QuotesContract.View) -> QuotesPresenter(view = view, repository = get()) } bind QuotesContract.Presenter::class

    }

}


