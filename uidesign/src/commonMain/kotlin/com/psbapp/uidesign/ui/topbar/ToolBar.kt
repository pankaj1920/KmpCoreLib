package com.psbapp.uidesign.ui.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight

@Composable
fun ToolBar(
    titleModifier: Modifier = Modifier,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    title: String = "",
    titleStyle: TextStyle = blackStyle.copy(fontSize = TextDimension.sp14),
    fontWeight: TextWeight = TextWeight.SEMI_BOLD
) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leadingContent?.let {
                leadingContent()
            }
            trailingContent?.let {
                trailingContent()
            }
        }
        PSBText(
            text = title,
            style = titleStyle,
            textAlign = TextAlign.Center,
            modifier = titleModifier.fillMaxWidth(),
            fontWeight = fontWeight
        )
    }


}




