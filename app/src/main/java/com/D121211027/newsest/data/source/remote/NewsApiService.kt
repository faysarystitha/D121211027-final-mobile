package com.D121211027.newsest.data.source.remote

import com.D121211027.newsest.data.response.NewsResponse
import retrofit2.http.GET

interface NewsApiService {
    @GET("cnn-news")
    suspend fun getNews(): NewsResponse
}