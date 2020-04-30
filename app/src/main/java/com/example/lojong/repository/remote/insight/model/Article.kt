package com.example.lojong.repository.remote.insight.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Article(
    val id: Int,
    val text: String,
    val title: String,
    val url: String,
    val image_url: String
) : Parcelable