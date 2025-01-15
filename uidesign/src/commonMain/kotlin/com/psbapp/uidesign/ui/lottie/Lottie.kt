package com.psbapp.uidesign.ui.lottie

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.psbapp.appres.CoreRes.FileRes
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieUi(
    lottieFilePath:String,
    animationIterations: Int = Compottie.IterateForever,
    size: Dp = MaterialDimension.dp100,
    bgColor: Color = Color.Transparent,
    onAnimationEnded: () -> Unit = {}
) {
    val introAnimationComposition by rememberLottieComposition() {
        LottieCompositionSpec.DotLottie(
            FileRes.readBytes(lottieFilePath),
        )
    }
    val animationState = animateLottieCompositionAsState(
        composition = introAnimationComposition,
        iterations = animationIterations,
        isPlaying = true
    )
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