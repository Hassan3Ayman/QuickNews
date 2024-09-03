package com.kamel.client.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.smartapps.rscc.ui.navigation.LocalNavController
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

private const val DELAY_TIME = 1500L

@Composable
fun <T> EventHandler(
    effects: SharedFlow<T>,
    handleEffect: (T, NavController) -> Unit,
) {
    val navController = LocalNavController.current
    LaunchedEffect(key1 = Unit) {
        effects.throttleFirst(DELAY_TIME).collectLatest { effect ->
            handleEffect(effect, navController)
        }
    }
}