package com.example.lojong.base.extensions


import android.content.ComponentCallbacks
import com.example.lojong.base.BasePresenter
import com.example.lojong.base.BaseView
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier

inline fun <reified T : BasePresenter<*>> ComponentCallbacks.injectPresenter(
    view: BaseView<T>,
    qualifier: Qualifier? = null
    ) = lazy { get<T>(qualifier) { parametersOf(view) } }