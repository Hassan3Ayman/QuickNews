package com.example.quicknews.di

import android.content.Context
import androidx.room.Room
import com.example.quicknews.data.local.db.NewsDao
import com.example.quicknews.data.local.db.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "news_db"

    @Singleton
    @Provides
    fun provideNewsDB(
        @ApplicationContext context: Context,
    ): NewsDatabase =
        Room.databaseBuilder(context, NewsDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDatabase): NewsDao = db.newsDao()
}