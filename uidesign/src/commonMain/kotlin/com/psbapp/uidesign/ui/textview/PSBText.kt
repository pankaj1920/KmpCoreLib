@file:OptIn(ExperimentalResourceApi::class)

package com.psbapp.uidesign.ui.textview

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import gesundheitskiosk.resources.coreres.generated.resources.Res
import gesundheitskiosk.resources.coreres.generated.resources.satoshi_bold
import gesundheitskiosk.resources.coreres.generated.resources.satoshi_edium
import gesundheitskiosk.resources.coreres.generated.resources.satoshi_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@Composable
fun PSBText(
    text: String,
    fontWeight: TextWeight = TextWeight.REGULAR,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    style: TextStyle = LocalTextStyle.current
) {
    val fontFamily = getFontFamily(fontWeight)
    Text(
        text = text,
        modifier = modifier,
        color = color,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        fontWeight = FontWeight.ExtraBold,
        onTextLayout = onTextLayout,
        style = style.copy(fontFamily = fontFamily),
    )
}


@Composable
fun getFontFamily(fontWeight: TextWeight): FontFamily {
    return when (fontWeight) {
        TextWeight.REGULAR -> FontFamily(Font(Res.font.satoshi_regular))
        TextWeight.MEDIUM -> FontFamily(Font(Res.font.satoshi_edium))
        TextWeight.SEMI_BOLD -> FontFamily(Font(Res.font.satoshi_edium))
        TextWeight.BOLD -> FontFamily(Font(Res.font.satoshi_bold))
        TextWeight.EXTRA_BOLD -> FontFamily(Font(Res.font.satoshi_bold))
    }
}
