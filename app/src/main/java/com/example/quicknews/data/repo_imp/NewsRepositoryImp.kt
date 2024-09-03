package com.example.quicknews.data.repo_imp

import com.example.quicknews.data.local.db.NewsDao
import com.example.quicknews.data.remote.NewsService
import com.example.quicknews.data.remote.dto.ArticleDto
import com.example.quicknews.data.repo_imp.mapper.toArticle
import com.example.quicknews.data.repo_imp.mapper.toArticleLocalDto
import com.example.quicknews.domain.entities.Article
import com.example.quicknews.domain.repo.NewsRepository
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val newsService: NewsService,
    private val newsDao: NewsDao
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
                val articles = response.body()?.articles ?: emptyList()
                insertArticlesToDB(articles)
                return getSavedArticles()
            } else {
                throw Exception("Something went wrong")
            }
        } catch (e: UnknownHostException) {
            throw Exception("No internet connection")
        } catch (io: IOException) {
            throw Exception("Something went wrong")
        }
    }

    override suspend fun getArticleDetails(id: String): Article {
        return try {
            newsDao.getArticleDetails(id).toArticle()
        } catch (e: Exception) {
            throw Exception("Something went wrong")
        }
    }

    override suspend fun getSavedArticles(): List<Article> {
        return try {
            newsDao.getAllArticles().map { it.toArticle() }
        } catch (e: Exception) {
            throw Exception("Something went wrong")
        }
    }

    private suspend fun insertArticlesToDB(articles: List<ArticleDto>) {
        try {
            newsDao.clearAllArticles()
            newsDao.insertAllArticles(articles.map { it.toArticleLocalDto() })
        } catch (e: Exception) {
            throw Exception("Something went wrong")
        }
    }
}