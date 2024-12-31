package com.psbapp.uidesign.ui.textview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.ui.checkbox.PSBCheckBoxSmall
import com.psbapp.uidesign.ui.spacer.HorizontalSpacer


@Composable
fun PSBTermsConditionPolicyTxt(
    text: String,
    showCheckbox: Boolean = true,
    fontWeight: TextWeight = TextWeight.MEDIUM,
    actionFontWeight: TextWeight = TextWeight.BOLD,
    fontSize: TextUnit = TextDimension.sp9,
    actionFontSize: TextUnit = TextDimension.sp9,
    textAlign: TextAlign = TextAlign.Center,
    onCheckedChange: (Boolean) -> Unit
) {
    var checkedState by remember { mutableStateOf(true) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (showCheckbox) {
            PSBCheckBoxSmall(
                isCheck = checkedState,
                onClick = {
                    checkedState = !checkedState
                    onCheckedChange(checkedState)
                })
            HorizontalSpacer(MaterialDimension.dp10)
        }
        val termsCondition = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = MaterialThemeColor.textGreyColor, fontWeight = FontWeight.Normal,
                    fontSize = fontSize, fontFamily = getFontFamily(fontWeight)
                )
            ) {
                append(text)
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialThemeColor.actionColor, fontWeight = FontWeight.ExtraBold,
                    fontSize = actionFontSize, fontFamily = getFontFamily(actionFontWeight)
                )
            ) {
                append(" Terms & Conditions ")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialThemeColor.textGreyColor, fontWeight = FontWeight.Normal,
                    fontSize = fontSize, fontFamily = getFontFamily(fontWeight)
                )
            ) {
                append("and")
            }
            withStyle(
                style = SpanStyle(
                    color = MaterialThemeColor.actionColor, fontWeight = FontWeight.ExtraBold,
                    fontSize = actionFontSize, fontFamily = getFontFamily(actionFontWeight)

                )
            ) {
                append(" PrivacyPolicy. ")
            }
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = termsCondition,
            textAlign = textAlign
        )

    }
}
