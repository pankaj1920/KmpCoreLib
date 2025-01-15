package com.psbapp.uidesign.ui.shimmer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PSBShimmerView(
    modifier: Modifier = Modifier,
    shimmerType: ShimmerType = ShimmerType.VERTICAL,
    isLoading: Boolean,
    shimmerContent: @Composable () -> Unit,
    contentAfterLoading: @Composable () -> Unit,

    ) {
    if (isLoading) {
        if (shimmerType == ShimmerType.VERTICAL) {
            VerticalShimmer(shimmerContent = shimmerContent)
        } else {
            HorizontalShimmer(shimmerContent = shimmerContent)
        }
    } else {
        contentAfterLoading()
    }
}

@Composable
fun VerticalShimmer(modifier: Modifier = Modifier, shimmerContent: @Composable () -> Unit) {
    LazyColumn {
        items(20) {
            shimmerContent()
        }
    }
}

@Composable
fun HorizontalShimmer(modifier: Modifier = Modifier, shimmerContent: @Composable () -> Unit) {
    LazyRow {
        items(20) {
            shimmerContent()
        }
    }
}
