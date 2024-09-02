package com.example.quicknews.domain.repo

import com.example.quicknews.domain.entities.Article

interface NewsRepository {

    suspend fun getCategories(): List<String>
    suspend fun getCategoryArticles(category: String): List<Article>
}