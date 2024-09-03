package com.example.quicknews.ui.navigation

import androidx.lifecycle.SavedStateHandle

sealed class Screens(val route: String) {
    data object Home : Screens("home")
    data object ArticleDetails : Screens("article_details")
}

class ScreensArgs(savedStateHandle: SavedStateHandle) {
    val id: String = savedStateHandle[ID] ?: ""

    companion object {
        const val ID = "id"
    }
}