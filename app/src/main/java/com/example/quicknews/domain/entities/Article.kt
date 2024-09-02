package com.example.quicknews.domain.entities

import com.google.gson.annotations.SerializedName

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val imageUrl: String,
    val publishedAt: String,
    val content: String,
)
