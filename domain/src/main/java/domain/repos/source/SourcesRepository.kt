package domain.repos.source

import domain.models.SourcesItemDTO

interface SourcesRepository {
    suspend fun getSources(apiKey: String, category: String): List<SourcesItemDTO?>?
}
