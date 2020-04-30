package com.example.lojong.repository.remote.insight.resources

import com.example.lojong.repository.remote.insight.responses.ArticlesResponse
import com.example.lojong.repository.remote.insight.model.Quote
import com.example.lojong.repository.remote.insight.responses.ServiceResponse
import com.example.lojong.repository.remote.insight.model.Video

interface RemoteInsightResources {
    suspend fun getVideos(): ServiceResponse<List<Video>>
    suspend fun getArticles(): ServiceResponse<ArticlesResponse>
    suspend fun getQuotes(): ServiceResponse<List<Quote>>
}