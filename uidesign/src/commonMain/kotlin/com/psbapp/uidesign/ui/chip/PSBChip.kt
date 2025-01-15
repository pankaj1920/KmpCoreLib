package com.psbapp.uidesign.ui.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.BgColor
import com.psbapp.uidesign.theme.colors.MaterialThemeColor
import com.psbapp.uidesign.theme.dimension.MaterialDimension
import com.psbapp.uidesign.theme.typography.blackStyle
import com.psbapp.uidesign.ui.imageview.PSBBgNetworkImage
import com.psbapp.uidesign.ui.imageview.PSBImageView
import com.psbapp.uidesign.ui.textview.PSBText
import com.psbapp.uidesign.ui.textview.TextWeight
import com.psbapp.uidesign.utils.modifier.onClick
import org.jetbrains.compose.resources.DrawableResource


@Composable
fun PSBChip(
    modifier: Modifier = Modifier,
    chipTextModifier: Modifier = Modifier,
    bgChipColor: Color = Color.White,
    image: DrawableResource? = null,
    textAlign: TextAlign = TextAlign.Start,
    paddingValues: PaddingValues = PaddingValues(MaterialDimension.dp3),
    title: String,
    index: Int,
    fontSize: TextUnit = 14.sp,
    fontWeight: TextWeight = TextWeight.REGULAR,
    onClick: (position: Int, title: String) -> Unit,
    isSelected: Boolean = false
) {

    Column {
        Row(
            modifier
                .fillMaxWidth()
                .onClick {
                    onClick(index, title)
                }
                .background(
                    if (isSelected) MaterialThemeColor.primaryDarkColor else bgChipColor,
                    shape = RoundedCornerShape(35.dp)
                )
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            image?.let {
                PSBImageView(
                    image = it,
                    modifier = Modifier
                        .size(35.dp)
                        .background(
                            BgColor,
                            shape = CircleShape
                        )
                        .padding(6.dp),
                )
            }
            //Fill MaxWith make isse for Search Category
            PSBText(
                modifier = chipTextModifier,
                text = title,
                style = blackStyle.copy(
                    fontSize = fontSize,
                    color = if (isSelected) MaterialThemeColor.whiteColor else MaterialThemeColor.blackColor
                ),
                textAlign = textAlign,
                fontWeight = fontWeight
            )

        }

    }

}

@Composable
fun PSBChipNetworkImg(
    modifier: Modifier = Modifier,
    chipTextModifier: Modifier = Modifier,
    bgChipColor: Color = Color.White,
    image: String? = null,
    textAlign: TextAlign = TextAlign.Start,
    paddingValues: PaddingValues = PaddingValues(MaterialDimension.dp3),
    title: String,
    index: Int,
    fontSize: TextUnit = 14.sp,
    fontWeight: TextWeight = TextWeight.REGULAR,
    onClick: (position: Int, title: String) -> Unit,
    isSelected: Boolean = false
) {

    Column {
        Row(
            modifier
                .fillMaxWidth()
                .onClick {
                    onClick(index, title)
                }
                .background(
                    if (isSelected) MaterialThemeColor.primaryDarkColor else bgChipColor,
                    shape = RoundedCornerShape(35.dp)
                )
                .padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            image?.let {
                PSBBgNetworkImage(
                    url = it,
                    bgColor = MaterialThemeColor.primaryLightColor,
                    imageSize = DpSize(
                        width = MaterialDimension.dp15,
                        height = MaterialDimension.dp15
                    ),
                    shape = CircleShape,

                    )
            }
            //Fill MaxWith make isse for Search Category
            PSBText(
                modifier = chipTextModifier,
                text = title,
                style = blackStyle.copy(
                    fontSize = fontSize,
                    color = if (isSelected) MaterialThemeColor.whiteColor else MaterialThemeColor.blackColor
                ),
                textAlign = textAlign,
                fontWeight = fontWeight
            )

        }

    }

}
