package com.psbapp.uidesign.theme.colors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

val ThemeColorsPalette = compositionLocalOf { ColorsPalette() }

val MaterialThemeColor: ColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = ThemeColorsPalette.current


val PSBLightColorPalette = ColorsPalette()

val PSBDarkColorsPalette = ColorsPalette()





