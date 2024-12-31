package com.psbapp.uidesign.ui.dot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.psbapp.uidesign.theme.dimension.MaterialDimension

@Composable
fun PSBDot(
    size: Dp = MaterialDimension.dp8,
    color: Color = Color.Red
) {
    Box(
        modifier = Modifier.size(size).background(color, CircleShape)
    )
}