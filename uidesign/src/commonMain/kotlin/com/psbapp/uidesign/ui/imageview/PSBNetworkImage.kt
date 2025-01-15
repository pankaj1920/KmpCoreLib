package com.psbapp.uidesign.ui.imageview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.AsyncImagePainter.State
import coil3.compose.LocalPlatformContext
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.size.Precision
import coil3.util.DebugLogger
import com.psbapp.appres.CoreRes
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.ui.shimmer.shimmerEffect
import com.psbapp.uidesign.utils.modifier.ifCondition
import com.psbapp.utils.EMPTY
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun PSBNetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    imageSize: DpSize,
    bgColor: Color? = null,
    tintColor: Color? = null,
    contentDescription: String = String.EMPTY,
    contentScale: ContentScale = ContentScale.Crop,
    roundCorner: Dp = MaterialDimension.dp8,
    shape: Shape = RoundedCornerShape(roundCorner),
    onSuccess: () -> Unit = {},
    onError: (State.Error) -> Unit = {},
    placeholder: DrawableResource = CoreRes.Drawable.img_placeholder,
    errorPainter: DrawableResource = CoreRes.Drawable.img_placeholder
) {

    var isImageLoading by remember { mutableStateOf(true) }
    val context = LocalPlatformContext.current


    val imageModifier = if (imageSize.width == 0.dp) {
        modifier.fillMaxWidth().height(imageSize.height)
    } else {
        modifier.size(imageSize)
    }

    Box(modifier = Modifier.then(imageModifier)) {

        if (url.isNotEmpty()) {
            if (isImageLoading) {
                Box(
                    modifier = modifier.clip(shape)
                        .shimmerEffect(isImageLoading)
                        .then(imageModifier)
                )
            }

            coil3.compose.AsyncImage(
                modifier = modifier.clip(shape)
                    .ifCondition(bgColor != null) {
                        background(bgColor!!, shape)
                    }.then(imageModifier),
                model = ImageRequest.Builder(context).diskCacheKey(url.trim())
                    .memoryCachePolicy(CachePolicy.ENABLED).memoryCacheKey(url.trim()).data(url)
                    .precision(Precision.EXACT).build(),
                contentDescription = contentDescription,
                contentScale = contentScale,
                colorFilter = tintColor?.let { ColorFilter.tint(it) },
                placeholder = painterResource(resource = placeholder),
                error = painterResource(resource = errorPainter),
                onError = {
                    isImageLoading = false
                    onError(it)
                },
                onLoading = {
                    isImageLoading = true
                },
                onSuccess = {
                    isImageLoading = false
                    onSuccess()
                },
                filterQuality = FilterQuality.Low

            )
        }
    }

}

fun getAsyncImageLoader(context: PlatformContext) = ImageLoader.Builder(context).memoryCache {
    MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true)
        .weakReferencesEnabled(true).build()
}.crossfade(true).logger(DebugLogger()).build()