package com.example.quicknews.data.local.local_dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticleLocalDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)
