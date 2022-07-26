package com.rochim.finalprojectschoters.models

data class NewsResponse(
    val articles: List<Article>,
    val totalResults: Int?,
    val status: String?
)