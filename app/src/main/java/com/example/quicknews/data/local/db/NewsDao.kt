package com.example.quicknews.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quicknews.data.local.local_dto.ArticleLocalDto

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllArticles(articles: List<ArticleLocalDto>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<ArticleLocalDto>

    @Query("DELETE FROM articles")
    suspend fun clearAllArticles()

    @Query("SELECT * FROM articles WHERE id = :id")
    suspend fun getArticleDetails(id: String): ArticleLocalDto
}