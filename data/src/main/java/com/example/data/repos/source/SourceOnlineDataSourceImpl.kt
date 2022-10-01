package com.example.data.repos.source

import com.example.data.api.webService
import com.example.data.models.convertTo
import domain.models.SourceResponseDTO
import domain.models.SourcesItemDTO
import domain.repos.source.SourceOnlineDataSource
import javax.inject.Inject

class SourceOnlineDataSourceImpl @Inject constructor(val webService: webService) :
    SourceOnlineDataSource {
    override suspend fun getSources(apiKey: String, category: String): List<SourcesItemDTO?>? {
        try {
            val result = webService.sources(apiKey, category)
            return result.convertTo(SourceResponseDTO::class.java).sources

        } catch (ex: Exception) {
            throw ex
        }
    }
}