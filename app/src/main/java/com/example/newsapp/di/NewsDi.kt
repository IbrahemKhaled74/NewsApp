package com.example.newsapp.di

import com.example.newsapp.ui.news.NewsDetails
import com.example.newsapp.ui.news.newsAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object NewsDi {
    @Provides
    fun provideNewsAdaptor(): newsAdaptor {
        return newsAdaptor(null)
    }

    @Provides
    fun provideNewsDetails(): NewsDetails {
        return NewsDetails()
    }
}