package com.psbapp.uidesign.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.dimension.TextDimension
import com.psbapp.uidesign.theme.typography.whiteStyle
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import com.psbapp.uidesign.utils.modifier.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PSBIconButton(
    text: String,
    modifier: Modifier = Modifier,
    height: Dp = MaterialDimension.dp40,
    textAlign: TextAlign = TextAlign.Center,
    fontWeight: TextWeight = TextWeight.SEMI_BOLD,
    style: TextStyle = whiteStyle.copy(fontSize = TextDimension.sp10),
    bgColors: Color = MaterialThemeColor.primaryDarkColor,
    roundCorner: Shape = RoundedCornerShape(MaterialDimension.dp10),
    iconTint: Color = MaterialThemeColor.whiteColor,
    leadingIcon: DrawableResource? = null,
    trailingIcon: DrawableResource? = null,
    leadingIconSize: Dp = MaterialDimension.dp20,
    trailingIconSize: Dp = MaterialDimension.dp15,
    paddingValues: PaddingValues = PaddingValues(horizontal = MaterialDimension.dp10),
    onClick:() ->Unit
) {
    Box(
        modifier = modifier.onClick {
            onClick()
        }.height(height)
            .background(bgColors, shape = roundCorner).padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            leadingIcon?.let {
                Icon(
                    modifier = Modifier.size(leadingIconSize),
                    painter = painterResource(it),
                    contentDescription = "",
                    tint = iconTint
                )
            }

            PSBText(
                text = text,
                modifier = Modifier.weight(1f),
                textAlign = textAlign,
                style = style,
                fontWeight = fontWeight
            )

            trailingIcon?.let {
                Icon(
                    modifier = Modifier.size(trailingIconSize),
                    painter = painterResource(it),
                    contentDescription = "",
                    tint = iconTint
                )
            }

        }


    }

}