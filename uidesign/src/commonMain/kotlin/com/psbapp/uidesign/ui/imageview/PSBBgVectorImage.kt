package com.psbapp.uidesign.ui.imageview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun PSBBgVectorImage(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    image: DrawableResource,
    bgColor: Color,
    alpha: Float = 0.3f,
    roundCorner: Dp = MaterialDimension.dp8,
    shape: Shape = RoundedCornerShape(roundCorner),
    padding: PaddingValues = PaddingValues
        (MaterialDimension.dp4)
) {
    Box(
        modifier = modifier
            .background(
                color = bgColor.copy(alpha = alpha),
                shape = shape
            )
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        PSBVectorImageView(modifier = imageModifier, image = image, tintColor = bgColor)
    }
}

@Composable
fun PSBBgCircleImage(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    padding: Dp = MaterialDimension.dp4
) {
    Box(
        modifier = modifier
            .background(
                color = MaterialThemeColor.whiteColor,
                shape = CircleShape
            )
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        PSBVectorImageView(image = image)
    }
}

@Composable
fun PSBBImage(
    image: DrawableResource,
    bgColor: Color,
    alpha: Float = 0.3f,
    roundCorner: Dp = MaterialDimension.dp8,
    padding: Dp = MaterialDimension.dp4
) {
    Box(
        modifier = Modifier
            .background(
                color = bgColor.copy(alpha = alpha),
                shape = RoundedCornerShape(roundCorner)
            )
            .padding(padding),
        contentAlignment = Alignment.Center
    ) {
        PSBImageView(image = image)
    }
}