package com.psbapp.uidesign.ui.textview

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.utils.modifier.onClick

@Composable
fun PSBTextWithAction(
    text: String,
    actionText: String,
    textColor: Color = MaterialThemeColor.blackColor,
    actionTextColor: Color = MaterialThemeColor.secondaryDarkColor,
    fontWeight: TextWeight = TextWeight.MEDIUM,
    actionFontWeight: TextWeight = TextWeight.BOLD,
    fontSize: TextUnit = TextDimension.sp9,
    actionFontSize: TextUnit = TextDimension.sp9,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(textAlign = TextAlign.Center),
    onActionClick: () -> Unit
) {
    val annotatedText = buildAnnotatedString {
        // Regular text
        withStyle(
            style = SpanStyle(
                color = textColor,
                fontFamily = getFontFamily(fontWeight),
                fontSize = fontSize
            )
        ) {
            append(text)
        }
        append(" ") // Space between text and actionText
        // Action text
        pushStringAnnotation(tag = "ACTION", annotation = actionText)
        withStyle(
            style = SpanStyle(
                color = MaterialThemeColor.actionColor, fontWeight = FontWeight.ExtraBold,
                fontSize = actionFontSize, fontFamily = getFontFamily(actionFontWeight)
            )
        ) {
            append(actionText)
        }
        pop() // End the annotation
    }

    Text(
        text = annotatedText,
        modifier = modifier.fillMaxWidth().onClick {
            annotatedText.getStringAnnotations(
                tag = "ACTION",
                start = 0,
                end = annotatedText.length
            ).firstOrNull()?.let {
                onActionClick()
            }
        },
        style = style
    )
}
