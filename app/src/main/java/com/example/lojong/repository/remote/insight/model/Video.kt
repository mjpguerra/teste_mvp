package com.example.lojong.repository.remote.insight.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Video(
    val id: Int,
    val name: String,
    val description: String,
    val url2: String,
    val aws_url: String,
    val image_url: String
) : Parcelable