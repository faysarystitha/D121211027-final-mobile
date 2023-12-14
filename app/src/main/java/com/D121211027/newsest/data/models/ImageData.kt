package com.D121211027.newsest.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ImageData(
    @SerialName("small")
    val small: String?,
    @SerialName("large")
    val large: String?
) : Parcelable