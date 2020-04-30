@file:Suppress("USELESS_CAST")

package com.example.lojong.util
import com.example.lojong.base.di.appModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Unconfined
import org.koin.dsl.module.module


val dispatcherTestModule = module {
    factory { Unconfined as CoroutineDispatcher }
}

val testApp = listOf(
    appModule,
        dispatcherTestModule
)