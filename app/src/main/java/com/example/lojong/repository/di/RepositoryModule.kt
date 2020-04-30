package com.example.lojong.repository.di


import com.example.lojong.BuildConfig
import com.example.lojong.repository.Repository
import com.example.lojong.repository.remote.Api
import com.example.lojong.repository.remote.insight.services.InsightServices
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object RepositoryModule {

    val modules = module {

        /** Repository */
        single { Repository(androidApplication()) }

        /** Passenger Services */
        factory {
            Api<InsightServices>().create(
                InsightServices::class.java,
                BuildConfig.BASE_API_URL
            )
        }
    }
}