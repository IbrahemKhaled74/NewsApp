package domain.repos.news

import domain.models.ArticlesItemDTO

interface NewsOnlineDataSource {
    suspend fun getNews(apiKey: String, source: String): MutableList<ArticlesItemDTO?>?
    suspend fun getNewsBySearch(
        apiKey: String,
        source: String,
        query: String
    ): MutableList<ArticlesItemDTO?>?

}