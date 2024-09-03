package com.example.quicknews.ui.screens.article_details

sealed class ArticleDetailsEvent {
    data class ReadMoreContent(val url: String) : ArticleDetailsEvent()
    data class ShareUrl(val url: String) : ArticleDetailsEvent()
}