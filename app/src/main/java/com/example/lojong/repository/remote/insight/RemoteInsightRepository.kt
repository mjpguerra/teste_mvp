package com.example.lojong.repository.remote.insight

import com.example.lojong.base.extensions.awaitResult
import com.example.lojong.repository.remote.insight.responses.ArticlesResponse
import com.example.lojong.repository.remote.insight.model.Quote
import com.example.lojong.repository.remote.insight.responses.ServiceResponse
import com.example.lojong.repository.remote.insight.model.Video
import com.example.lojong.repository.remote.insight.resources.RemoteInsightResources
import com.example.lojong.repository.remote.insight.services.InsightServices

class RemoteInsightRepository(private val insightServices: InsightServices):
    RemoteInsightResources {

    override suspend fun getVideos(): ServiceResponse<List<Video>> {
        return insightServices.getVideos().awaitResult()
    }

    override suspend fun getArticles(): ServiceResponse<ArticlesResponse> {
        return insightServices.getArticles().awaitResult()
    }

    override suspend fun getQuotes(): ServiceResponse<List<Quote>> {
        return insightServices.getQuotes().awaitResult()
    }
}