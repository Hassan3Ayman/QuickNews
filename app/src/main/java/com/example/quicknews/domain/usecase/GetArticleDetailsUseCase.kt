package com.example.quicknews.domain.usecase

import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import javax.inject.Inject

class GetArticleDetailsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(id: String): Article {
        return repository.getArticleDetails(id)
    }
}