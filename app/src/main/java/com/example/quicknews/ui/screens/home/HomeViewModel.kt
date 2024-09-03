package com.example.quicknews.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quicknews.domain.usecase.GetCategoriesUseCase
import com.example.quicknews.domain.usecase.GetCategoryArticlesUseCase
import com.example.quicknews.domain.usecase.GetSavedArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategoriesUseCase,
    private val getCategoryArticles: GetCategoryArticlesUseCase,
    private val getSavedArticles: GetSavedArticlesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val categories = getCategories()
            _state.update {
                it.copy(
                    categories = categories,
                    selectedCategory = categories.first()
                )
            }
            getArticles()
        }
    }

    fun onCategorySelected(category: String) {
        _state.update {
            it.copy(
                selectedCategory = category
            )
        }
        viewModelScope.launch {
            getArticles()
        }
    }

    fun onGetSavedArticles() {
        _state.update {
            it.copy(isLoading = true, isError = false)
        }
        try {
            viewModelScope.launch(Dispatchers.IO) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        articles = getSavedArticles().map { it.toArticleUiState() }
                    )
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

    private suspend fun getArticles() {
        _state.update {
            it.copy(isLoading = true, isError = false)
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update {
                    it.copy(
                        isLoading = false,
                        articles = getCategoryArticles(_state.value.selectedCategory).map { it.toArticleUiState() }
                    )
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

}