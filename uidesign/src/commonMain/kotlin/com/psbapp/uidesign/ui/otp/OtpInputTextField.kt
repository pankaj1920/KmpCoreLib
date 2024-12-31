package com.psbapp.uidesign.ui.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.LocalDimensions
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import com.psbapp.utils.EMPTY
import kotlinx.coroutines.delay


@Composable
fun OtpInputTextField(
    otpText: String,
    otpLength: Int,
    onOtpModified: (otp: String, otpFilled: Boolean) -> Unit,
    showInvalidOTP: Boolean,
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused },
        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
        onValueChange = {
            val filteredText = it.text.filter { char -> char.isDigit() }
            if (filteredText.length <= otpLength) {
                onOtpModified.invoke(filteredText, filteredText.length == otpLength)
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done
        ),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    CharacterContainer(
                        index = index,
                        text = otpText,
                        showInvalidOTP = showInvalidOTP,
                        isFocused = isFocused && index == otpText.length
                    )
                    Spacer(modifier = Modifier.width(MaterialDimension.dp10))
                }
            }
        }
    )
}

@Composable
internal fun CharacterContainer(
    index: Int,
    text: String,
    showInvalidOTP: Boolean,
    isFocused: Boolean,
) {
    val cursorVisible = remember { mutableStateOf(false) }
    val character = if (index < text.length) text[index].toString() else String.EMPTY

    LaunchedEffect(key1 = isFocused) {
        if (isFocused) {
            cursorVisible.value = true
            while (true) {
                delay(500L)
                cursorVisible.value = !cursorVisible.value
            }
        } else {
            cursorVisible.value = false
        }
    }

    Box(
        modifier = Modifier
            .size(MaterialDimension.dp45)
            .border(
                width = MaterialDimension.dp1,
                color = if (showInvalidOTP) {
                    MaterialThemeColor.otpErrorColor
                } else if (isFocused) {
                    MaterialThemeColor.otpFocusColor
                } else {
                    MaterialThemeColor.otpUnFocusColor
                },
                shape = RoundedCornerShape(LocalDimensions.current.dp6)
            ).background(
                color = MaterialThemeColor.whiteColor,
                shape = RoundedCornerShape(LocalDimensions.current.dp6)
            ),
        contentAlignment = Alignment.Center
    ) {
        PSBText(
            text = character,
            style = blackStyle.copy(fontSize = TextDimension.sp14),
            fontWeight = TextWeight.BOLD,
            textAlign = TextAlign.Center,
            color = MaterialThemeColor.otpTextColor,
            maxLines = 1
        )
        AnimatedVisibility(visible = isFocused && cursorVisible.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(MaterialDimension.dp2)
                    .height(MaterialDimension.dp20)
                    .background(MaterialThemeColor.otpFocusColor)
            )
        }
    }

}