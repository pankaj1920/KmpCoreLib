package com.psbapp.uidesign.ui.scrollview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.psbapp.uidesign.ui.spacer.VerticalSpacer

@Composable
fun PSBScrollView(
    modifier: Modifier = Modifier,
    bottomPadding: Dp = 20.dp,
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical =
        if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        reverseLayout = reverseLayout
    ) {
        item {
            content()
        }
        item {
            VerticalSpacer(bottomPadding)
        }
    }
}
