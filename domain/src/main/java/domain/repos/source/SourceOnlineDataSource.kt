package domain.repos.source

import domain.models.SourcesItemDTO


interface SourceOnlineDataSource {
    suspend fun getSources(apiKey: String, category: String): List<SourcesItemDTO?>?

}