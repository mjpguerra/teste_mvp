package com.example.lojong.repository.remote

import com.example.lojong.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

class Api<T> {

    private val nullOnEmptyConverterFactory = object : Converter.Factory() {
        fun converterFactory() = this
        override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) =
            object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
    }

    private val gson = GsonBuilder().setLenient().create()

    fun create(clazz: Class<T>, url: String): T {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(createClientWithInterceptor())
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(clazz)
    }

    private fun createClientWithInterceptor(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor { chain ->
                chain.proceed(
                    chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", BuildConfig.AUTHORIZATION)
                        .build()
                )
            }
            .build()
    }
}