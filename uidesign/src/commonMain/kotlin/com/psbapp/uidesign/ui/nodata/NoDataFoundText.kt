package com.psbapp.uidesign.ui.nodata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.theme.typography.primaryDarkStyle
import com.psbapp.uidesign.ui.textview.PSBText

@Composable
fun NoDataFoundText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PSBText(
            text = text,
            style = primaryDarkStyle.copy(
                fontSize = TextDimension.sp14,
                textAlign = TextAlign.Center
            )
        )
    }
}

