package com.psbapp.uidesign.ui.edittext

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.theme.typography.greyStyle

@Composable
fun PSBOutlineTextField(
    modifier: Modifier = Modifier,
    showErrorText: Boolean = false,
    boxModifier: Modifier = Modifier,
    textFiledBgColor: Color = Color.Transparent,
    onValueChange: (String) -> Unit,
    hint: String = "",
    maxLength: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle = blackStyle,
    hintStyle: TextStyle = greyStyle,
    enable: Boolean = true,
    singleLine: Boolean = true,
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        singleLine = true,
        label = { Text(hint) },
        textStyle = blackStyle,
        visualTransformation = VisualTransformation.None,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialThemeColor.blackColor,
            unfocusedTextColor = MaterialThemeColor.textGreyColor,
            focusedContainerColor = MaterialThemeColor.whiteColor,
            unfocusedContainerColor = MaterialThemeColor.whiteColor
        ),
        modifier = Modifier.fillMaxWidth()
    )

}
