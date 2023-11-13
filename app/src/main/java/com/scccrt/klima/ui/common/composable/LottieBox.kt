package com.scccrt.klima.ui.common.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieBox(resourceId: Int, modifier: Modifier = Modifier): Float {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(resourceId))
    val progress by animateLottieCompositionAsState(
        composition, iterations = 1
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = modifier
    )
    return progress
}