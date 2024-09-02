package com.example.quicknews.ui.screens.home

import com.example.quicknews.domain.entities.Article

data class HomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val categories: List<String> = emptyList(),
    val selectedCategory: String = "",
    val articles: List<ItemArticleUiState> = emptyList()
)

data class ItemArticleUiState(
    val title: String,
    val description: String,
    val publishedAt: String,
    val imageUrl: String,
)

fun Article.toArticleUiState() = ItemArticleUiState(
    title = title,
    description = description,
    publishedAt = publishedAt,
    imageUrl = imageUrl
)
