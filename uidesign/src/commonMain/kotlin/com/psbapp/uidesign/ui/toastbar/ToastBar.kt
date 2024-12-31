package com.psbapp.uidesign.ui.toastbar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.psbapp.uidesign.utils.modifier.ifCondition
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun rememberToastBarState(): ToastBarState {
    return remember { ToastBarState() }
}

@Composable
fun ToastBar(
    modifier: Modifier = Modifier,
    toastBarState: ToastBarState,
    verticalPadding: Dp = 12.sdp,
    horizontalPadding: Dp = 12.sdp,
    position: ToastBarPosition = ToastBarPosition.TOP,
    toastBarType: ToastBarType = ToastBarType.SUCCESS,
    contentBackgroundColor: Color? = null,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(durationMillis = 300),
        expandFrom = if (position == ToastBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(durationMillis = 300),
        shrinkTowards = if (position == ToastBarPosition.TOP)
            Alignment.Top else Alignment.Bottom
    ),
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .ifCondition(contentBackgroundColor != null) {
                background(color = contentBackgroundColor!!)
            }
    ) {
        content()

        when (toastBarType) {
            ToastBarType.SUCCESS -> SuccessBarComponent()
            ToastBarType.ERROR -> ErrorBarComponent()
            ToastBarType.INFO -> InfoBarComponent()
            ToastBarType.PENDING -> PendingBarComponent()
            ToastBarType.CUSTOM -> ToastBarComponent(position, toastBarState, enterAnimation)
        }

    }
}