package com.D121211027.newsest.data

import com.D121211027.newsest.data.repository.NewsRepository
import com.D121211027.newsest.data.source.remote.NewsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val newsRepository: NewsRepository
}

class DefaultAppContainer: AppContainer {
    private val url = "https://berita-indo-api-next.vercel.app/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(url)
        .build()

    private val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    override val newsRepository: NewsRepository
        get() = NewsRepository(retrofitService)
}
