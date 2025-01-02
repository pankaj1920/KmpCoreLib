package com.psbapp.uidesign.ui.statusbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun SetStatusBarColor(color: Color) {
    val uiColor = UIColor(
        red = color.red.toDouble(),
        green = color.green.toDouble(),
        blue = color.blue.toDouble(),
        alpha = color.alpha.toDouble()
    )

    // Create a view to overlay the status bar
    val statusBarFrame = UIApplication.sharedApplication.statusBarFrame
    val statusBarView = UIView(frame = statusBarFrame)
    statusBarView.backgroundColor = uiColor

    // Add the view to the key window
    UIApplication.sharedApplication.keyWindow?.addSubview(statusBarView)
}

@Composable
actual fun SetNavigationBarColor(color: Color) {
}