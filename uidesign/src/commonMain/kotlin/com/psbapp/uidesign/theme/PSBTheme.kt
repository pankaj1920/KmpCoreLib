package com.psbapp.uidesign.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import com.psbapp.uidesign.theme.colors.ColorsPalette
import com.psbapp.uidesign.theme.colors.ThemeColorsPalette
import com.psbapp.uidesign.theme.dimension.LocalDimensions
import com.psbapp.uidesign.theme.dimension.PSBDimens
import com.psbapp.uidesign.ui.imageview.getAsyncImageLoader


enum class ThemeType {
    PSB_THEME,
    APP_THEME
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PSBTheme(
    lightPalette: ColorsPalette = ThemeColorsPalette.current,
    darkPalette: ColorsPalette = ThemeColorsPalette.current,
    systemIsDark: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {

    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }

    val isDarkTheme by remember { mutableStateOf(systemIsDark) }
    val colors = if (isDarkTheme) darkPalette else lightPalette
    val psbDimens = PSBDimens


    // here is the important point, where you will expose custom objects
    CompositionLocalProvider(
        ThemeColorsPalette provides colors,
        LocalDimensions provides psbDimens,
    ) {

        SystemAppearance(false)
        MaterialTheme(
            content = content
        )
    }
}
