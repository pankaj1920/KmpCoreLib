package com.psbapp.uidesign.ui.view

import ContentWithMessageBar
import MessageBarPosition
import MessageBarState
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.psbapp.uidesign.theme.colors.BgColor
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.ui.imageview.PSBVectorImageView
import com.psbapp.uidesign.ui.loader.LottieLoadingUi
import com.psbapp.uidesign.ui.statusbar.SetNavigationBarColor
import com.psbapp.uidesign.ui.statusbar.SetStatusBarColor
import com.psbapp.uidesign.ui.toastbar.ToastBarPosition
import com.psbapp.uidesign.ui.toastbar.ToastBarState
import com.psbapp.uidesign.ui.toastbar.rememberToastBarState
import com.psbapp.uidesign.utils.modifier.ifCondition
import org.jetbrains.compose.resources.DrawableResource
import rememberMessageBarState

@Composable
fun ViewScreen(
    loading: Boolean = false,
    modifier: Modifier = Modifier,
    bgImage: DrawableResource? = null,
    backgroundColor: Color = BgColor,
    statusBarColor: Color? = null,
    navigationBarColor: Color? = null,
    isFullScreen: Boolean = false,
    snackBarModifier: Modifier = Modifier,
    toastBarState: ToastBarState = rememberToastBarState(),
    position: ToastBarPosition = ToastBarPosition.TOP,
    messageBarState: MessageBarState = rememberMessageBarState(),
    messageBarPosition: MessageBarPosition = MessageBarPosition.TOP,
    visibilityDuration: Long = 3000L,
    showCopyButton: Boolean = false,
    onMessageCopied: (() -> Unit)? = null,
    successIcon: ImageVector = Icons.Default.Check,
    errorIcon: ImageVector = Icons.Default.Warning,
    errorMaxLines: Int = 3,
    successMaxLines: Int = 3,
    contentBackgroundColor: Color = Color.Transparent,
    content: @Composable @UiComposable () -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    statusBarColor?.let { SetStatusBarColor(it) }
    navigationBarColor?.let { SetNavigationBarColor(it) }

    if (loading) {
        LottieLoadingUi(bgColor = backgroundColor)
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            bgImage?.let {
                PSBVectorImageView(
                    image = bgImage,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier.fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures { _ ->
                            keyboardController?.hide()
                        }
                    }
                    .ifCondition(isFullScreen.not()) { statusBarsPadding().navigationBarsPadding() }) {
                /* ToastBar(
                     toastBarState = toastBarState,
                     modifier = snackBarModifier,
                     position = position,
                     contentBackgroundColor = contentBackgroundColor,
                 ) {
                     content()
                 }*/

                ContentWithMessageBar(
                    messageBarState = messageBarState,
                    modifier = snackBarModifier,
                    position = messageBarPosition,
                    visibilityDuration = visibilityDuration,
                    showCopyButton = showCopyButton,
                    onMessageCopied = onMessageCopied,
                    successIcon = successIcon,
                    errorIcon = errorIcon,
                    errorMaxLines = errorMaxLines,
                    successMaxLines = successMaxLines,
                    contentBackgroundColor = contentBackgroundColor,
                    successContainerColor = MaterialThemeColor.primaryDarkColor,
                    successContentColor = MaterialThemeColor.whiteColor,
                    errorContainerColor = MaterialThemeColor.redColor,
                    errorContentColor = MaterialThemeColor.whiteColor,
                ) {
                    content()
                }

            }
        }
    }

}

