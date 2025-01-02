package com.psbapp.uidesign.ui.statusbar

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat

@Composable
actual fun SetStatusBarColor(color: Color) {
    val activity = LocalContext.current as? Activity
    activity?.window?.statusBarColor = color.toArgb()
    val decorView = activity?.window?.decorView
    if (decorView != null) {
        val windowInsetsController = WindowCompat.getInsetsController(activity.window, decorView)
        windowInsetsController.isAppearanceLightStatusBars = true
    }
}

@Composable
actual fun SetNavigationBarColor(color: Color) {
    val activity = LocalContext.current as? Activity
    activity?.window?.navigationBarColor = color.toArgb()
    val decorView = activity?.window?.decorView
    if (decorView != null) {
        val windowInsetsController = WindowCompat.getInsetsController(activity.window, decorView)
        windowInsetsController.isAppearanceLightNavigationBars =
            true // Set to true if you want light content
    }
}