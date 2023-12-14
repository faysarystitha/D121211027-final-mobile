package com.D121211027.newsest.data.repository

import com.D121211027.newsest.data.response.NewsResponse
import com.D121211027.newsest.data.source.remote.NewsApiService

class NewsRepository(
    private val newsApiService: NewsApiService
) {
    suspend fun getNews(): NewsResponse {
        return newsApiService.getNews()
    }
}