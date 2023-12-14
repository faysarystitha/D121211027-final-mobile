package com.D121211027.newsest.data.response

import android.os.Parcelable
import com.D121211027.newsest.data.models.Article
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class NewsResponse (
    @SerialName("messages")
    val messages: String?,
    @SerialName("total")
    val total: Int?,
    @SerialName("data")
    val data: List<Article>?
) : Parcelable