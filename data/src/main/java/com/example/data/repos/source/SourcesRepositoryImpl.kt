package com.example.data.repos.source

import domain.models.SourcesItemDTO
import domain.repos.source.SourceOnlineDataSource
import domain.repos.source.SourcesRepository
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor
    (val sourcesOnlineDataSource: SourceOnlineDataSource) : SourcesRepository {
    override suspend fun getSources(apiKey: String, category: String): List<SourcesItemDTO?>? {
        try {
            return sourcesOnlineDataSource.getSources(apiKey, category)

        } catch (ex: Exception) {
            throw ex
        }
    }
}