package com.kamel.client.ui.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

private const val ERROR_MESSAGE = "Time must be bigger than zero"
private const val REQUIRED_TIME = 0

fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > REQUIRED_TIME) { ERROR_MESSAGE }
    var lastTime = 0L
    return transform { value ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastTime >= periodMillis) {
            lastTime = currentTime
            emit(value)
        }
    }
}