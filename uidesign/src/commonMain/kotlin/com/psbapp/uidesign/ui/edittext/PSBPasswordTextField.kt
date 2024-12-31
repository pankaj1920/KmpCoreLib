package com.psbapp.uidesign.ui.edittext

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.theme.typography.greyStyle
import com.psbapp.uidesign.ui.edittext.validation.BaseValidationState
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.utils.resource.CoreDrawableRes
import gesundheitskiosk.resources.coreres.generated.resources.ic_password_hide
import gesundheitskiosk.resources.coreres.generated.resources.ic_password_show
import org.jetbrains.compose.resources.painterResource

@Composable
fun PSBPasswordTextField(
    modifier: Modifier = Modifier,
    showErrorText: Boolean = false,
    boxModifier: Modifier = Modifier,
    textFiledBgColor: Color = MaterialThemeColor.whiteColor,
    textState: BaseValidationState,
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLength: Int = 16,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = blackStyle,
    hintStyle: TextStyle = greyStyle,
    text: String? = textState.text,
    enable: Boolean = true,

    cursorBrush: SolidColor = SolidColor(MaterialThemeColor.primaryColor)
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = enable) {
        if (!enable) isPasswordVisible.value = false
    }

    val focusRequester = remember { FocusRequester() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = boxModifier
            .fillMaxWidth()
            .height(45.dp)
            .background(color = textFiledBgColor, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp)
    ) {
        BasicTextField(
            modifier = modifier.focusRequester(focusRequester),
            value = text.toString(),

            onValueChange = {
                if (it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            enabled = enable,
            keyboardActions = keyboardActions,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
            ),
            visualTransformation = when (isPasswordVisible.value) {
                true -> VisualTransformation.None
                false -> PasswordVisualTransformation()
            },
            textStyle = textStyle,
            cursorBrush = cursorBrush,

            decorationBox = { innerTextField ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp) // Adjust padding as needed
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (textState.text.isEmpty()) {
                            PSBText(text = hint, style = hintStyle.copy(fontSize = 13.sp))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                            IconButton(onClick = {
                                if (enable) {
                                    isPasswordVisible.value = !isPasswordVisible.value
                                }
                            }) {

                                when (isPasswordVisible.value) {
                                    true -> Icon(
                                        painter = painterResource(CoreDrawableRes.ic_password_show),
                                        contentDescription = null,
                                        tint = MaterialThemeColor.primaryDarkColor,
                                    )

                                    false -> Icon(
                                        painter = painterResource(CoreDrawableRes.ic_password_hide),
                                        contentDescription = null,
                                        tint = MaterialThemeColor.primaryDarkColor,
                                    )
                                }
                            }
                        }

                    }

                }
            }
        )
    }

}

