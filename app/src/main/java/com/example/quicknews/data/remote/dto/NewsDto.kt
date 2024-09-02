package com.example.quicknews.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("status") val status: String?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("articles") val articles: List<ArticleDto>
)