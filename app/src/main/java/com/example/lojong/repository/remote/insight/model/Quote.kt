package com.example.lojong.repository.remote.insight.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Quote(
    val id: Int,
    val text: String,
    val order: Int
) : Parcelable