package com.psbapp.uidesign.ui.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.ui.textview.PSBText
import kotlinx.coroutines.delay

@Composable
fun PSBBorderButton(
    text: String,
    modifier: Modifier = Modifier,
    enable: Boolean = true,
    textStyle: TextStyle = blackStyle,
    borderColor: Color = MaterialThemeColor.textGreyColor,
    delayMillis: Long = 1000L,
    onClick: () -> Unit,
) {
    var isClickable by remember { mutableStateOf(true) }
    LaunchedEffect(isClickable) {
        if (!isClickable) {
            delay(delayMillis)
            isClickable = true
        }
    }
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        enabled = enable,
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(6.dp),
        onClick = {
            if (isClickable) {
                isClickable = false
                onClick.invoke()
            }
        }, elevation = ButtonDefaults.elevatedButtonElevation(2.dp)
    ) {

        PSBText(text = text, style = textStyle)
    }
}