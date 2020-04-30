package com.example.lojong.repository.remote.insight.services

import com.example.lojong.repository.remote.insight.responses.ArticlesResponse
import com.example.lojong.repository.remote.insight.model.Quote
import com.example.lojong.repository.remote.insight.model.Video
import retrofit2.Call
import retrofit2.http.GET

interface InsightServices {

    @GET("videos")
    fun getVideos(): Call<List<Video>>

    @GET("articles")
    fun getArticles(): Call<ArticlesResponse>

    @GET("quotes")
    fun getQuotes(): Call<List<Quote>>


}