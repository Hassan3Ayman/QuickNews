package com.example.quicknews.ui.navigation

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object ArticleDetails : Screens("article_details")
}