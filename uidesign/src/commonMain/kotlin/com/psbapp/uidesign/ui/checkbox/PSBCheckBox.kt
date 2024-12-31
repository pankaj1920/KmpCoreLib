package com.psbapp.uidesign.ui.checkbox


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.LocalDimensions
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.utils.modifier.onClick
import gesundheitskiosk.resources.coreres.generated.resources.Res
import gesundheitskiosk.resources.coreres.generated.resources.ic_check_white
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PSBCheckBoxLarge(
    modifier: Modifier = Modifier,
    isCheck: Boolean = false,
    checkedColor: Color = MaterialThemeColor.primaryColor,
    uncheckedColor: Color = MaterialThemeColor.greyColor,
    isEnable: Boolean = true,
    onClick: () -> Unit,
    icon: DrawableResource = Res.drawable.ic_check_white,
) {
    PSBCheckboxContainer(
        onClick = onClick,
        modifier = modifier,
        checkedColor = checkedColor,
        uncheckedColor = uncheckedColor,
        isEnable = isEnable,
    ) {
        Box(
            modifier =
            Modifier
                .size(LocalDimensions.current.dp20)
                .background(if (isCheck) checkedColor else MaterialThemeColor.whiteColor)
                .padding(LocalDimensions.current.dp3),
            contentAlignment = Alignment.Center,
        ) {
            if (isCheck) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = MaterialThemeColor.whiteColor,
                )
            }
        }
    }
}

@Composable
fun PSBCheckBoxSmall(
    modifier: Modifier = Modifier,
    isCheck: Boolean = false,
    checkBoxSize: Dp = MaterialDimension.dp13,
    checkedColor: Color = MaterialThemeColor.primaryDarkColor,
    uncheckedColor: Color = MaterialThemeColor.greyColor,
    isEnable: Boolean = true,
    onClick: () -> Unit,
    icon: DrawableResource = Res.drawable.ic_check_white,
) {
    PSBCheckboxContainer(
        onClick = onClick,
        modifier = modifier,
        checkedColor = checkedColor,
        uncheckedColor = uncheckedColor,
        isEnable = isEnable,
    ) {
        Box(
            modifier =
            Modifier
                .size(checkBoxSize)
                .background(if (isCheck) checkedColor else MaterialThemeColor.whiteColor)
                .padding(LocalDimensions.current.dp3),
            contentAlignment = Alignment.Center,
        ) {
            if (isCheck) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    tint = MaterialThemeColor.whiteColor,
                )
            }
        }
    }
}

@Composable
private fun PSBCheckboxContainer(
    modifier: Modifier = Modifier,
    checkedColor: Color,
    isCheck: Boolean = false,
    uncheckedColor: Color,
    isEnable: Boolean = true,
    onClick: () -> Unit,
    container: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
            Modifier.onClick {
                if (isEnable) onClick()
            },
        ) {
            Card(
                modifier = Modifier.background(MaterialThemeColor.whiteColor),

                shape = RoundedCornerShape(LocalDimensions.current.dp4),
                border =
                BorderStroke(
                    width = LocalDimensions.current.dp1,
                    color = if (isCheck) checkedColor else uncheckedColor,
                ),
            ) {
                container()
            }
        }
    }
}

