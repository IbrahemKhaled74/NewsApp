package com.example.data.repos.news

import com.example.data.api.webService
import com.example.data.models.convertTo
import domain.models.ArticlesItemDTO
import domain.models.NewsResponseDTO
import domain.repos.news.NewsOnlineDataSource
import javax.inject.Inject

class NewsOnlineDataSourceImpl
@Inject constructor(val webService: webService) : NewsOnlineDataSource {
    val API_KEY = "66c4c0a11a804109a8ea511820fd1d97"

    override suspend fun getNews(
        apiKey: String, source: String
    )
            : MutableList<ArticlesItemDTO?>? {
        try {
            val result = webService.news(API_KEY, source, "")
            return result.convertTo(NewsResponseDTO::class.java).articles

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getNewsBySearch(
        apiKey: String,
        source: String,
        query: String
    ): MutableList<ArticlesItemDTO?>? {
        val result = webService.news(API_KEY, source, query)
        return result.convertTo(NewsResponseDTO::class.java).articles


    }
}