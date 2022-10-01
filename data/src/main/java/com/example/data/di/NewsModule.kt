package com.example.data.di

import com.example.data.api.webService
import com.example.data.repos.news.NewsOnlineDataSourceImpl
import com.example.data.repos.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import domain.repos.news.NewsOnlineDataSource
import domain.repos.news.NewsRepository

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {
    @Provides
    fun provideNewsOnline(webService: webService): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webService)
    }

    @Provides
    fun provideNews(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImpl(newsOnlineDataSource)
    }

}