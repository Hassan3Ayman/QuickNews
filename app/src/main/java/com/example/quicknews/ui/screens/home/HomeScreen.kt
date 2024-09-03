package com.example.quicknews.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quicknews.ui.screens.components.ErrorState
import com.example.quicknews.ui.screens.components.LoadingState
import com.example.quicknews.ui.screens.home.components.ArticleItem
import com.example.quicknews.ui.screens.home.components.CategoryItem
import com.smartapps.rscc.ui.navigation.LocalNavController
import com.smartapps.rscc.ui.navigation.navigateToArticleDetails
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    HomeContent(
        state = state,
        onCategorySelected = viewModel::onCategorySelected,
        onGetSavedArticles = viewModel::onGetSavedArticles
    )
}

@Composable
private fun HomeContent(
    state: HomeUiState,
    onCategorySelected: (String) -> Unit,
    onGetSavedArticles: () -> Unit,
    modifier: Modifier = Modifier
) {
    val localNavController = LocalNavController.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .background(Color.White)
    ) {
        val scrollState = rememberLazyListState()

        Spacer(modifier = Modifier.height(16.dp))
        LazyRow(
            contentPadding = PaddingValues(start = 16.dp, end = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.categories) {
                CategoryItem(
                    title = it,
                    isSelected = (it == state.selectedCategory),
                    onSelected = { category ->
                        onCategorySelected(category)
                        scope.launch {
                            if (state.articles.isNotEmpty()){
                                scrollState.animateScrollToItem(0)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(
            modifier = Modifier.weight(1f),
            visible = state.isLoading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LoadingState(modifier = Modifier.fillMaxSize())
        }
        AnimatedVisibility(
            modifier = Modifier.weight(1f),
            visible = state.isError,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorState(
                onRetry = onGetSavedArticles,
                text = state.errorMessage,
                modifier = Modifier.fillMaxSize()
            )
        }
        AnimatedVisibility(
            visible = !state.isLoading && !state.isError,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(state.articles, key = { it.title }) {
                    ArticleItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        item = it,
                        onClick = { localNavController.navigateToArticleDetails(it.id) }
                    )
                }
            }
        }
    }
}