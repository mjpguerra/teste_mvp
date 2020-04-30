package com.example.lojong.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}