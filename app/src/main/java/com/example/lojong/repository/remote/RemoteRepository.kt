package com.example.lojong.repository.remote


import com.example.lojong.repository.remote.insight.RemoteInsightRepository
import com.example.lojong.repository.remote.insight.resources.RemoteInsightResources
import com.example.lojong.repository.remote.insight.services.InsightServices
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteRepository : RemoteFactory, KoinComponent {

    private val insightServices: InsightServices by inject()

    override val insightResources: RemoteInsightResources = RemoteInsightRepository(insightServices)

}