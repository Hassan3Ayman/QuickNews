package com.example.quicknews.data.repo_imp

import com.example.quicknews.data.remote.NewsService
import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsService: NewsService
): NewsRepository {
    override suspend fun getCategoryArticles(category: String): List<Article> {
        TODO("Not yet implemented")
    }
}