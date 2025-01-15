package com.psbapp.uidesign.ui.scrollview


import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.psbapp.uidesign.theme.dimension.MaterialDimension

/**
 * Calculates the total height of a grid based on the list of items and item height.
 *
 * @param items The list of items to display in the grid.
 * @param itemHeight The height of each item in the grid.
 * @param itemSpacing The vertical spacing between rows (default is 8.dp).
 * @param columns The number of columns in the grid (default is 3).
 * @return The total height of the grid as a [Dp] value.
 */
@Composable
fun  getGridHeight(
    listSize: Int,
    itemHeight: Dp,
    itemSpacing: Dp = MaterialDimension.dp0,
    columns: Int = 3
): Dp {
    val totalRows = (listSize+ columns - 1) / columns
    return (totalRows * itemHeight.value + (totalRows - 1) * itemSpacing.value).dp
}

@Composable
fun Modifier.gridHeight(
    listSize: Int,
    itemHeight: Dp,
    itemSpacing: Dp = MaterialDimension.dp0,
    columns: Int = 3
): Modifier {
    val totalRows = (listSize + columns - 1) / columns
    val calculatedHeight = (totalRows * itemHeight.value + (totalRows - 1) * itemSpacing.value).dp
    return this.then(Modifier.heightIn(max= calculatedHeight))
}
