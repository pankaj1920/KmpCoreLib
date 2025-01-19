package com.psbapp.uidesign.ui.ratingbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.psbapp.appres.CoreDrawableRes
import com.psbapp.uidesign.ui.imageview.PSBVectorImageView
import com.psbapp.utils.EMPTY
import org.jetbrains.compose.resources.DrawableResource
import syncride.kmpcorelib.resources.generated.resources.ic_selected_star
import syncride.kmpcorelib.resources.generated.resources.ic_unselected_star


@Composable
fun PSBStarRatingBar(
    modifier: Modifier = Modifier,
    starModifier: Modifier = Modifier,
    unSelectedStar: DrawableResource = CoreDrawableRes.ic_unselected_star,
    selectedStar: DrawableResource = CoreDrawableRes.ic_selected_star,
    maxStars: Int = 5,
    rating: Float,
    starSpacing: Float = 10f,
    onRatingChanged: (Float) -> Unit
) {
    val density = LocalDensity.current.density
    val starSize = (10f * density).dp
    val starSpace = (starSpacing * density).dp

    Row(
        modifier = modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxStars) {
            val isSelected = i <= rating
            val icon = if (isSelected) selectedStar else unSelectedStar
            val iconTintColor = if (isSelected) Color(0xFFFEAD1D) else Color(0xFFFEAD1D)
            PSBVectorImageView(
                image = icon,
                contentDescription = String.EMPTY,
                tintColor = iconTintColor,
                modifier = starModifier
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onRatingChanged(i.toFloat())
                        }
                    )
                    .width(starSize).height(starSize)
            )

            if (i < maxStars) {
                Spacer(modifier = Modifier.width(starSpace))
            }
        }
    }
}