package com.psbapp.uidesign.theme.style

/*

object PSBTextStyle : BaseTextStyle {
    override val H1Bold @Composable get() = H1SemiBold
    override val H1Regular @Composable get() = H1Medium

    override val H1SemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.h1TextSize,
                lineHeight = PwFontSize.H1LineHeight,
            )

    override val H1Medium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.h1TextSize,
                lineHeight = PwFontSize.H1LineHeight,
            )
    override val H2Bold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.h2TextSize,
                lineHeight = PwFontSize.H2LineHeight,
            )

    override val H2Regular
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                fontSize = PwFontSize.h2TextSize,
                lineHeight = PwFontSize.H2LineHeight,
            )

    override val H2SemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.h2TextSize,
                lineHeight = PwFontSize.H2LineHeight,
            )

    override val H2Medium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.h2TextSize,
                lineHeight = PwFontSize.H2LineHeight,
            )
    override val H3Regular @Composable get() = H3SemiBold

    override val H3Bold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.h3TextSize,
                lineHeight = PwFontSize.H3LineHeight,
            )

    override val H3SemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.h3TextSize,
                lineHeight = PwFontSize.H3LineHeight,
            )

    override val H4Bold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.h4TextSize,
                lineHeight = PwFontSize.H4LineHeight,
            )

    override val H4SemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.h4TextSize,
                lineHeight = PwFontSize.H4LineHeight,
            )

    override val H4Medium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.h4TextSize,
                lineHeight = PwFontSize.H4LineHeight,
            )
    override val H4Regular @Composable get() = H4Medium
    override val BodyRegBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.bodyRegularTextSize,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )

    override val BodyRegSemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.bodyRegularTextSize,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )

    override val BodyRegMedium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.bodyRegularTextSize,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )
    override val BodyRegRegular @Composable get() = BodyRegMedium

    override val BodyLight
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyRegularTextSize,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )

    override val BodyRegUnderLine
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyRegularTextSize,
                textDecoration = TextDecoration.Underline,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )

    override val BodyRegStrike
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyRegularTextSize,
                textDecoration = TextDecoration.LineThrough,
                lineHeight = PwFontSize.BodyRegularLineHeight,
            )

    override val BodySmallBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.bodySmallTextSize,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )

    override val BodySmallSemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.bodySmallTextSize,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )

    override val BodySmallMedium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.bodySmallTextSize,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )
    override val BodySmallRegular @Composable get() = BodySmallMedium

    override val BodySmallLight
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodySmallTextSize,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )

    override val BodySmallUnderLine
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodySmallTextSize,
                textDecoration = TextDecoration.Underline,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )

    override val BodySmallStrike
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodySmallTextSize,
                textDecoration = TextDecoration.LineThrough,
                lineHeight = PwFontSize.BodySmallLineHeight,
            )

    override val BodyTinyBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                fontSize = PwFontSize.bodyTinyTextSize,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )

    override val BodyTinySemiBold
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilySemiBold,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.SemiBold,
                fontSize = PwFontSize.bodyTinyTextSize,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )

    override val BodyTinyMedium
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyMedium,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Medium,
                fontSize = PwFontSize.bodyTinyTextSize,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )
    override val BodyTinyRegular @Composable get() = BodyTinyMedium

    override val BodyTinyLight
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyTinyTextSize,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )

    override val BodyTinyUnderLine
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyTinyTextSize,
                textDecoration = TextDecoration.Underline,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )

    override val BodyTinyStrike
        @Composable get() =
            TextStyle(
                fontFamily = PwFontFamilyRegular,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Light,
                fontSize = PwFontSize.bodyTinyTextSize,
                textDecoration = TextDecoration.LineThrough,
                lineHeight = PwFontSize.BodyTinyLineHeight,
            )
}*/
