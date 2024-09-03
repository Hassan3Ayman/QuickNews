package com.example.quicknews.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.quicknews.R

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val composition by
        rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
        LottieAnimation(
            modifier = Modifier
                .size(150.dp)
                .rotate(180f),
            composition = composition,
            iterations = 50
        )
    }
}