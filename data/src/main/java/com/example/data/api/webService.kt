package com.example.data.api

import com.example.data.models.NewsResponse
import com.example.data.models.SourceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface webService {
    @GET("v2/top-headlines/sources")
    suspend fun sources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
    ): SourceResponse

    @GET("v2/everything")
    suspend fun news(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String,
        @Query("q") q: String,
    ): NewsResponse
}