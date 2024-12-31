package com.psbapp.uidesign.ui.bottomnav

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import org.jetbrains.compose.resources.painterResource

@Composable
fun PSBBottomNavBar(
    bottomNavItemList: List<BottomNavItemModel>,
    onBottomNavClick: (route: Any) -> Unit,
) {
    var navigationSelectedItem by remember { mutableStateOf(0) }



    NavigationBar {
        //getting the list of bottom navigation items for our data class
        bottomNavItemList.forEachIndexed { index, navigationItem ->

            val fontWeight =
                if (index == navigationSelectedItem) TextWeight.SEMI_BOLD  else TextWeight.REGULAR
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                label = {
                    PSBText(navigationItem.title, fontWeight = fontWeight)
                },
                icon = {
                    Icon(
                        modifier = Modifier.size(MaterialDimension.dp20),
                        painter = painterResource(navigationItem.selectedIcon),
                        contentDescription = navigationItem.title,
                    )
                },
                onClick = {
                    navigationSelectedItem = index
                    onBottomNavClick(navigationItem.route)
                },
                colors= NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialThemeColor.whiteColor,
                    selectedTextColor = MaterialThemeColor.primaryDarkColor,
                    indicatorColor = MaterialThemeColor.primaryDarkColor
                )

            )
        }
    }

}
