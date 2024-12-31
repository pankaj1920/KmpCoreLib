package com.psbapp.uidesign.ui.bottomnav

import androidx.compose.ui.graphics.Color
import com.psbapp.uidesign.theme.colors.PrimaryDarkColor
import com.psbapp.uidesign.theme.colors.SecondaryDarkColor
import org.jetbrains.compose.resources.DrawableResource

data class BottomNavItemModel(
    val route: Any,
    val title: String,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
    val selectedColor: Color = PrimaryDarkColor,
    val unselectedColor: Color = SecondaryDarkColor,
    val badgeCount: Int? = null,
)