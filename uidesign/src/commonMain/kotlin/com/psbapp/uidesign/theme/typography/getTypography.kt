package com.psbapp.uidesign.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.psbapp.uidesign.theme.colors.ActionColor
import com.psbapp.uidesign.theme.colors.PrimaryDarkColor
import com.psbapp.uidesign.theme.colors.SecondaryDarkColor
import com.psbapp.uidesign.theme.colors.TextBlack
import com.psbapp.uidesign.theme.colors.TextBlue
import com.psbapp.uidesign.theme.colors.TextGreen
import com.psbapp.uidesign.theme.colors.TextGrey
import com.psbapp.uidesign.theme.colors.TextWhite

val normalStyle = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
)

val boldStyle =
    TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )


val extraBoldStyle =
    TextStyle(
        fontWeight = FontWeight.ExtraBold,
        fontSize = 24.sp
    )

val semiBoldStyle =
    TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp
    )


val blackStyle = normalStyle.copy(color = TextBlack)
val whiteStyle = normalStyle.copy(color = TextWhite)
val greyStyle = normalStyle.copy(color = TextGrey)
val blueStyle = normalStyle.copy(color = TextBlue)
val greenStyle = normalStyle.copy(color = TextGreen)
val primaryStyle = normalStyle.copy(color = PrimaryDarkColor)
val primaryDarkStyle = normalStyle.copy(color = PrimaryDarkColor)
val secondaryDarkStyle = normalStyle.copy(color = SecondaryDarkColor)
val actionStyle = normalStyle.copy(color = ActionColor)

