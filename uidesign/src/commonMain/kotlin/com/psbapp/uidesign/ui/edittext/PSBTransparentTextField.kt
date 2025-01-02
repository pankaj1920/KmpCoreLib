package com.psbapp.uidesign.ui.edittext

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.theme.typography.greyStyle
import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState
import com.psbapp.uidesign.ui.textview.PSBText

@Composable
fun PSBTransparentTextField(
    modifier: Modifier = Modifier,
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
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = text.toString(),
        onValueChange = {
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        enabled = enable,
        keyboardOptions = keyboardOptions.copy(imeAction = ImeAction.Done),
        keyboardActions = keyboardActions,
        textStyle = textStyle,
        cursorBrush = cursorBrush,

        decorationBox = { innerTextField ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp) // Adjust padding as needed
            ) {

                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                    if (textState.text.isEmpty()) {
                        PSBText(text = hint, style = hintStyle.copy(fontSize = 13.sp))
                    }
                    innerTextField()
                }

            }
        }
    )

}


