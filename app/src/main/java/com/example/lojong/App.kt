package com.example.lojong

import android.app.Application
import android.content.Context
import com.example.lojong.base.application.StarterApplication
import com.example.lojong.base.di.PresenterModule
import com.example.lojong.repository.di.RepositoryModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    companion object {
        lateinit var appContextApplication: Context
    }

    private val starterApplication by inject<StarterApplication>()


    override fun onCreate() {
        super.onCreate()
        /** Set App Context */
        appContextApplication = applicationContext

        startKoin {
            androidContext(this@App)
            modules(PresenterModule.modules +
                    RepositoryModule.modules

            )
        }

        starterApplication.start(this)

    }


}