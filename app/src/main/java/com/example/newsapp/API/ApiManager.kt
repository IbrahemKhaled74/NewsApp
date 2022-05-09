package com.example.newsapp.API

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object {
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit {
            if (retrofit == null) {
                val logging = HttpLoggingInterceptor { message -> Log.e("Api", message) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofit!!
        }

        fun getApis(): webService {
            return getInstance().create(webService::class.java)
        }
    }
}