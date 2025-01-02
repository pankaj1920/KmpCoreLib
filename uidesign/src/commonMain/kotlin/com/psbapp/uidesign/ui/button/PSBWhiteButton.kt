package com.psbapp.uidesign.ui.button

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.typography.whiteStyle
import com.psbapp.uidesign.ui.textview.PSBText

@Composable
fun PSBWhiteButton(
    text: String,
    textStyle: TextStyle = whiteStyle,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialThemeColor.whiteColor),
        shape = RoundedCornerShape(6.dp),
        onClick = { onClick() }) {
        PSBText(
            text = text,
            style = textStyle.copy(fontSize = 18.sp, color = MaterialThemeColor.primaryColor)
        )
    }
}

