package com.psbapp.uidesign.ui.edittext

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.theme.typography.greyStyle
import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState
import com.psbapp.utils.getCoroutineScope
import kotlinx.coroutines.async

@Composable
fun PSBTextField(
    modifier: Modifier = Modifier,
    showErrorText: Boolean = false,
    boxModifier: Modifier = Modifier,
    textFiledBgColor: Color = MaterialThemeColor.whiteColor,
    textState: BaseValidationState,
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = blackStyle,
    hintStyle: TextStyle = greyStyle,
    text: String? = textState.text,
    enable: Boolean = true,
    singleLine: Boolean = true,
    cursorBrush: SolidColor = SolidColor(MaterialThemeColor.primaryColor)
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = boxModifier
            .fillMaxWidth()
            .height(45.dp)
            .background(color = textFiledBgColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp)
    ) {
        PSBTransparentTextField(
            modifier = modifier,
            onValueChange = { onValueChange(it) },
            hint = hint,
            textState = textState,
            maxLength = maxLength,
            hintStyle = hintStyle,
            text = text,
            enable = enable,
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            textStyle = textStyle,
            cursorBrush = cursorBrush,
        )
    }
    AnimatedVisibility(visible = showErrorText) {
        val errorText = getCoroutineScope().async {
            textState.getError()
        }
//        textState.getError().let { error -> ErrorText(textError = error) }

    }

}
