package com.example.quicknews.ui.screens.home

sealed class HomeEvent {
    data class NavigateToArticleDetails(val id: String) : HomeEvent()
}