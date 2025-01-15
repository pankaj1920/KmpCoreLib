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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import coil3.compose.AsyncImagePainter.State
import com.psbapp.appres.CoreDrawableRes
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.utils.EMPTY
import gesundheitskiosk.core.resources.generated.resources.img_placeholder
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun PSBBgVectorImage(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    image: DrawableResource,
    bgColor: Color,
    tintColor: Color = bgColor,
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
        PSBVectorImageView(modifier = imageModifier, image = image, tintColor = tintColor)
    }
}

@Composable
fun PSBBgNetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String = String.EMPTY,
    contentScale: ContentScale = ContentScale.Crop,
    onSuccess: () -> Unit = {},
    onError: (State.Error) -> Unit = {},
    placeholder: DrawableResource = CoreDrawableRes.img_placeholder,
    errorPainter: DrawableResource = CoreDrawableRes.img_placeholder,
    imageModifier: Modifier = Modifier,
    bgColor: Color,
    tintColor: Color? = null,
    alpha: Float = 0.3f,
    roundCorner: Dp = MaterialDimension.dp8,
    shape: Shape = RoundedCornerShape(roundCorner),
    imageSize: DpSize,
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
        PSBNetworkImage(
            modifier = imageModifier,
            url = url,
            contentDescription = contentDescription,
            contentScale = contentScale,
            onSuccess = onSuccess,
            onError = onError,
            placeholder = placeholder,
            errorPainter = errorPainter,
            imageSize = imageSize,
            shape = shape,
            tintColor = tintColor,
            roundCorner = roundCorner
        )
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