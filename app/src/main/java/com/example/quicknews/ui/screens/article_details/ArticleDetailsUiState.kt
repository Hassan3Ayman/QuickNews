package com.example.quicknews.ui.screens.article_details

import com.example.quicknews.domain.entities.Article

data class ArticleDetailsUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val title: String = "",
    val content: String = "",
    val url: String = "",
    val author: String = "",
    val publishedAt: String = "",
    val imageUrl: String = "",
)


fun Article.toArticleDetailsUiState() = ArticleDetailsUiState(
    isLoading = false,
    isError = false,
    errorMessage = "",
    title = title,
    author = author,
    url = url,
    content = content,
    publishedAt = publishedAt,
    imageUrl = imageUrl
)