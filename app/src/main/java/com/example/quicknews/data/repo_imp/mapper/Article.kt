package com.example.quicknews.data.repo_imp.mapper

import com.example.quicknews.data.local.local_dto.ArticleLocalDto
import com.example.quicknews.data.remote.dto.ArticleDto
import com.example.quicknews.domain.entities.Article

fun ArticleDto.toArticle(): Article {
    return Article(
        id = "",
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

fun ArticleLocalDto.toArticle(): Article {
    return Article(
        id = id.toString(),
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        imageUrl = urlToImage.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}

fun ArticleDto.toArticleLocalDto(): ArticleLocalDto {
    return ArticleLocalDto(
        author = author.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        url = url.orEmpty(),
        urlToImage = imageUrl.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        content = content.orEmpty()
    )
}