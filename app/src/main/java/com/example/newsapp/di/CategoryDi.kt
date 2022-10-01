package com.example.newsapp.di

import com.example.newsapp.ui.category.categoryAdaptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoryDi {

    @Provides
    fun provideCategoryAdaptor(): categoryAdaptor {
        return categoryAdaptor(null)
    }
}