package com.D121211027.newsest.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Article (
    @SerialName("title")
    val title: String?,
    @SerialName("link")
    val link: String?,
    @SerialName("contentSnippet")
    val contentSnippet: String?,
    @SerialName("isoDate")
    val isoDate: String?,
    @SerialName("image")
    val image: ImageData?
) : Parcelable