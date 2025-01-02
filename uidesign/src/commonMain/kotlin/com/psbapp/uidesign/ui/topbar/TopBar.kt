package com.psbapp.uidesign.ui.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.psbapp.uidesign.theme.dimension.MaterialDimension

import com.psbapp.uidesign.ui.imageview.PSBVectorImageView
import com.psbapp.uidesign.utils.modifier.onClick
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun TopBar(
    trailingIcon: DrawableResource? = null,
    leadingIcon: DrawableResource? = null,
    leadingIconModifier: Modifier = Modifier,
    leadingIconTint: Color = Color.Black,
    trailingIconModifier: Modifier = Modifier,
    trailingIconTint: Color = Color.Black,
    titleModifier: Modifier = Modifier,
    onBackClick: () -> Unit = { },
    trailingIconClick: () -> Unit = {},
    title: String = "",
    titleStyle: TextStyle = TextStyle.Default
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialDimension.dp12)
    ) {
        leadingIcon?.let {
            PSBVectorImageView(
                image = it,
                tintColor = leadingIconTint,
                modifier = leadingIconModifier
                    .onClick { onBackClick() }
                    .padding(vertical = MaterialDimension.dp5)

            )
        }
        Text(
            text = title,
            style = titleStyle,
            textAlign = TextAlign.Center,
            modifier = titleModifier.weight(1f)
        )
        trailingIcon?.let {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.weight(1f)) {
                PSBVectorImageView(
                    image = it,
                    tintColor = trailingIconTint,
                    modifier = trailingIconModifier
                        .onClick { trailingIconClick() }
                        .padding(vertical = MaterialDimension.dp5)

                )

            }
        }

    }


}




