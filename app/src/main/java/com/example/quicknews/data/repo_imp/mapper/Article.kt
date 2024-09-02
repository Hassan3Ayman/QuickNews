package com.example.quicknews.data.repo_imp.mapper

import com.example.quicknews.data.remote.dto.ArticleDto
import com.example.quicknews.domain.entities.Article

fun ArticleDto.toArticle(): Article{
    return Article(
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}