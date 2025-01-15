package com.psbapp.uidesign.ui.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.BgColor
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.colors.White
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.theme.typography.whiteStyle
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import com.psbapp.uidesign.utils.modifier.conditional
import com.psbapp.utils.ripple.NoRippleInteractionSource

@Composable
fun PSBTabLayout(
    isUpcomingTab: Boolean = false,
    bgSelectedColor: Color = MaterialThemeColor.primaryDarkColor,
    tabs: List<String>,
    onClickNavigation: @Composable (tabIndex: Int) -> Unit,
) {
    val tabIndex = rememberSaveable { mutableIntStateOf(0) }
    TabRow(
        containerColor = Color.Transparent,
        modifier = Modifier
            .background(
                color = if (isUpcomingTab) White else BgColor,
                shape = RoundedCornerShape(40.dp)
            )
            .padding(4.dp)
            .fillMaxWidth(),
        selectedTabIndex = tabIndex.value,
        indicator = {},
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .height(MaterialDimension.dp30)
                    .conditional(tabIndex.value == index,
                        modifier = {
                            background(
                                bgSelectedColor,
                                shape = RoundedCornerShape(40.dp)
                            )
                        }
                    ).conditional(tabIndex.value != index && isUpcomingTab) {
                        background(
                            White,
                            shape = RoundedCornerShape(40.dp)
                        )
                    },
                text = {
                    PSBText(
                        title,
                        style = if (tabIndex.value == index) whiteStyle.copy(
                            fontSize = 12.sp
                        ) else blackStyle.copy(fontSize = 12.sp),
                        fontWeight = TextWeight.MEDIUM
                    )
                },
                selected = tabIndex.value == index,
                selectedContentColor = Color.Transparent,
                interactionSource = NoRippleInteractionSource(),
                onClick = {
                    tabIndex.value = index

                }
            )
        }
    }
    onClickNavigation(tabIndex.value)

}
