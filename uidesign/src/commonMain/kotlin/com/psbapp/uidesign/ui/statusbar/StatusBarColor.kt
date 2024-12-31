package com.psbapp.uidesign.ui.statusbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun SetStatusBarColor(color: Color)

@Composable
expect fun SetNavigationBarColor(color: Color)