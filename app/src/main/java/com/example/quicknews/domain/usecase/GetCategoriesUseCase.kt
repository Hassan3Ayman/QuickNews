package com.example.quicknews.domain.usecase

import com.example.quicknews.domain.repo.NewsRepository
import javax.inject.Inject


class GetCategoriesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): List<String> {
        return repository.getCategories()
    }
}