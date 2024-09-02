package com.example.quicknews.domain.usecase

import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import javax.inject.Inject

class GetCategoryArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(category: String): List<Article> {
        return repository.getCategoryArticles(category)
    }
}