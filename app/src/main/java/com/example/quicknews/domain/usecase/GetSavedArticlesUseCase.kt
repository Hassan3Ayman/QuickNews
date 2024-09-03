package com.example.quicknews.domain.usecase

import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import javax.inject.Inject

class GetSavedArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<Article> {
        return repository.getSavedArticles()
    }
}