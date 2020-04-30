package com.example.lojong.repository.remote.insight.responses

import android.os.Parcelable
import com.example.lojong.repository.remote.insight.model.Article
import kotlinx.android.parcel.Parcelize

@Parcelize
class ArticlesResponse(
    val articles: List<Article>
) : Parcelable