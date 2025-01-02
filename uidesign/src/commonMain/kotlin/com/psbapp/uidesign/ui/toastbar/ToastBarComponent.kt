package com.psbapp.uidesign.ui.toastbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun SuccessBarComponent() {

}

@Composable
fun ErrorBarComponent() {
}

@Composable
fun InfoBarComponent() {
}

@Composable
fun PendingBarComponent() {
}

@Composable
fun ToastBarComponent(
    position: ToastBarPosition,
    toastBarState: ToastBarState,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(durationMillis = 300),
        expandFrom = if (position == ToastBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = if (position == ToastBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    )
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = if (position == ToastBarPosition.TOP)
            Arrangement.Top else Arrangement.Bottom
    ) {
        AnimatedVisibility(
            visible = true,
            enter = enterAnimation,
            exit = exitAnimation
        ) {

        }
    }
}