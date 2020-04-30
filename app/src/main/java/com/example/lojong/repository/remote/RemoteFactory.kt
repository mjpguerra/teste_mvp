package com.example.lojong.repository.remote

import com.example.lojong.repository.remote.insight.resources.RemoteInsightResources

interface RemoteFactory {
    val insightResources: RemoteInsightResources
}