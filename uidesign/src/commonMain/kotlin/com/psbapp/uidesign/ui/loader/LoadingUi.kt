package com.psbapp.uidesign.ui.loader

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.psbapp.appres.CoreRes.FileRes
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.LocalDimensions
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi


@Composable
fun CircularProgressLoadingUI(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(LocalDimensions.current.dp24),
            color = MaterialThemeColor.primaryDarkColor,
            strokeWidth = LocalDimensions.current.dp2
        )
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieLoadingUi(
    size: Dp = MaterialDimension.dp100,
    bgColor: Color = Color.Transparent,
    onAnimationEnded: () -> Unit = {}
) {
    val lottieFilePath = "files/app_loader.lottie"
    val introAnimationComposition by rememberLottieComposition() {
        LottieCompositionSpec.DotLottie(
            FileRes.readBytes(lottieFilePath),
        )
    }
    val animationState = animateLottieCompositionAsState(
        composition = introAnimationComposition,
        iterations = Compottie.IterateForever,
        isPlaying = true
    )
    Box(
        modifier = Modifier.fillMaxSize().background(bgColor),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(size),
            painter = rememberLottiePainter(
                composition = introAnimationComposition,
                progress = {
                    if (animationState.isAtEnd) {
                        onAnimationEnded()
                    }
                    animationState.progress
                }
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "Intro Lottie animation"
        )
    }
}