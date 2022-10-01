package com.example.data.repos.news

import domain.models.ArticlesItemDTO
import domain.repos.news.NewsOnlineDataSource
import domain.repos.news.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) :
    NewsRepository {
    val API_KEY = "66c4c0a11a804109a8ea511820fd1d97"

    override suspend fun getNews(apiKey: String, source: String): MutableList<ArticlesItemDTO?>? {
        try {
            return newsOnlineDataSource.getNews(API_KEY, source)

        } catch (ex: Exception) {
            throw ex
        }
    }

    override suspend fun getNewsBySearch(
        apiKey: String,
        source: String,
        query: String
    ): MutableList<ArticlesItemDTO?>? {
        try {
            return newsOnlineDataSource.getNewsBySearch(API_KEY, source, query)

        } catch (ex: Exception) {
            throw ex
        }


    }

}