package com.example.quicknews.ui.screens.article_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknews.domain.usecase.GetArticleDetailsUseCase
import com.example.quicknews.ui.navigation.ScreensArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val getArticleDetails: GetArticleDetailsUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(ArticleDetailsUiState())
    val state = _state.asStateFlow()
    private val args = ScreensArgs(savedStateHandle)
    init {
        getArticleDetails()
    }

    private fun getArticleDetails() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                   getArticleDetails(args.id).toArticleDetailsUiState()
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = true,
                    errorMessage = e.message ?: "Unknown error"
                )
            }
        }
    }
}