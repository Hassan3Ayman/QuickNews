package com.example.quicknews.domain.repo

import com.example.quicknews.data.remote.dto.ArticleDto
import com.example.quicknews.domain.entities.Article

interface NewsRepository {

    suspend fun getCategories(): List<String>
    suspend fun getCategoryArticles(category: String): List<Article>
    suspend fun getArticleDetails(id: String): Article
}