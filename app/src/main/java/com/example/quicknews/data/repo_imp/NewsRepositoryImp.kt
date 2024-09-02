package com.example.quicknews.data.repo_imp

import com.example.quicknews.data.remote.NewsService
import com.example.quicknews.data.repo_imp.mapper.toArticle
import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsService: NewsService
) : NewsRepository {
    override suspend fun getCategories(): List<String> {
        return listOf(
            "general",
            "technology",
            "business",
            "entertainment",
            "health",
            "science",
            "sports"
        )
    }

    override suspend fun getCategoryArticles(category: String): List<Article> {
        try {
            val response = newsService.getCategoryNews(category)
            if (response.isSuccessful) {
                return response.body()?.articles?.map { it.toArticle() } ?: emptyList()
            } else {
                throw Exception("Something went wrong")
            }
        } catch (e: UnknownHostException) {
            throw Exception("No internet connection")
        } catch (io: IOException) {
            throw Exception("Something went wrong")
        }
    }

}