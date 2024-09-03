package com.example.quicknews.ui.screens.article_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknews.domain.usecase.GetArticleDetailsUseCase
import com.example.quicknews.ui.navigation.ScreensArgs
import com.kamel.client.ui.util.throttleFirst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.shareIn
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

    private val _effect = MutableSharedFlow<ArticleDetailsEvent>()
    val effect = _effect.asSharedFlow().throttleFirst(1000).mapNotNull { it }
        .shareIn(viewModelScope, SharingStarted.Eagerly)

    private val args = ScreensArgs(savedStateHandle)
    init {
        getArticleDetails()
    }

    fun onReadMoreContent(url: String) {
        viewModelScope.launch {
            _effect.emit(ArticleDetailsEvent.ReadMoreContent(url))
        }
    }

    fun onShareUrl(url: String) {
        viewModelScope.launch {
            _effect.emit(ArticleDetailsEvent.ShareUrl(url))
        }
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