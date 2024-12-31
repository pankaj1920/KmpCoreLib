package com.psbapp.uidesign.ui.imageview

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource


@Composable
fun PSBImageView(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Crop,
) {
    Image(
        painter = painterResource(image),
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale
    )


}

@Composable
fun PSBImageView(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    tintColor: Color? = null,
    contentDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit,
) {
//    val isVector = image. == "svg" // Assuming the `DrawableResource` has an extension property.

    if (true) {
        Image(
            imageVector = vectorResource(image),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier,
            colorFilter = tintColor?.let { ColorFilter.tint(it) }
        )
    } else {
        Image(
            painter = painterResource(image),
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = modifier
        )
    }
}
