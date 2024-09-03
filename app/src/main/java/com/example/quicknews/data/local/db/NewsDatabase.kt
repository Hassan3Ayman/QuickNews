package com.example.quicknews.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicknews.data.local.local_dto.ArticleLocalDto

@Database(entities = [ArticleLocalDto::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}