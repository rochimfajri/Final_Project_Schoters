package com.rochim.finalprojectschoters.api

import com.rochim.finalprojectschoters.models.NewsResponse
import com.rochim.finalprojectschoters.util.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("everything")
    fun getNews(
        @Query("q") q: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String):Call<NewsResponse>

    @GET("top-headlines")
    fun getTopNews(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("apiKey") apiKey: String):Call<NewsResponse>

    @GET("everything")
    fun getSearchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String):Call<NewsResponse>
}