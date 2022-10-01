package com.example.data.di

import com.example.data.api.webService
import com.example.data.repos.source.SourceOnlineDataSourceImpl
import com.example.data.repos.source.SourcesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import domain.repos.source.SourceOnlineDataSource
import domain.repos.source.SourcesRepository

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {
    @Provides
    fun provideSourceOnline(webService: webService): SourceOnlineDataSource {
        return SourceOnlineDataSourceImpl(webService)
    }

    @Provides
    fun provideSource(sourceOnlineDataSource: SourceOnlineDataSource): SourcesRepository {
        return SourcesRepositoryImpl(sourceOnlineDataSource)
    }
}