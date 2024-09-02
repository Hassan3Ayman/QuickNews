package com.example.quicknews.di

import com.example.quicknews.data.repo_imp.NewsRepositoryImp
import com.example.quicknews.domain.repo.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNewsRepository(repository: NewsRepositoryImp): NewsRepository
}