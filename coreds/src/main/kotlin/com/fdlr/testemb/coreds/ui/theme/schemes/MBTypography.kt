package com.fdlr.testemb.coreds.ui.theme.schemes

import androidx.compose.runtime.Composable
import com.fdlr.testemb.coreds.theme.Foundation
import com.fdlr.testemb.coreds.ui.theme.AppFontFamily
import com.fdlr.testemb.coreds.ui.theme.Typography

internal val mbTypography: Typography
    @Composable
    get() = Typography().run {
        val fontFamily = AppFontFamily()
        copy(
            displayLarge = displayLarge.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseDisplayLargeFontSize,
                lineHeight = Foundation.mbMobileBaseDisplayLargeLineHeight,
                letterSpacing = Foundation.mbMobileBaseDisplayLargeLetterSpacing,
                fontWeight = Foundation.mbMobileBaseDisplayLargeFontWeight
            ),
            displayMedium = displayMedium.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseDisplayMediumFontSize,
                lineHeight = Foundation.mbMobileBaseDisplayMediumLineHeight,
                letterSpacing = Foundation.mbMobileBaseDisplayMediumLetterSpacing,
                fontWeight = Foundation.mbMobileBaseDisplayMediumFontWeight
            ),
            displaySmall = displaySmall.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseDisplaySmallFontSize,
                lineHeight = Foundation.mbMobileBaseDisplaySmallLineHeight,
                letterSpacing = Foundation.mbMobileBaseDisplaySmallLetterSpacing,
                fontWeight = Foundation.mbMobileBaseDisplaySmallFontWeight
            ),
            titleLarge = titleLarge.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseTitleLargeFontSize,
                lineHeight = Foundation.mbMobileBaseTitleLargeLineHeight,
                letterSpacing = Foundation.mbMobileBaseTitleLargeLetterSpacing,
                fontWeight = Foundation.mbMobileBaseTitleLargeFontWeight
            ),
            titleMedium = titleMedium.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseTitleMediumFontSize,
                lineHeight = Foundation.mbMobileBaseTitleMediumLineHeight,
                letterSpacing = Foundation.mbMobileBaseTitleMediumLetterSpacing,
                fontWeight = Foundation.mbMobileBaseTitleMediumFontWeight
            ),
            titleSmall = titleSmall.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseTitleSmallFontSize,
                lineHeight = Foundation.mbMobileBaseTitleSmallLineHeight,
                letterSpacing = Foundation.mbMobileBaseTitleSmallLetterSpacing,
                fontWeight = Foundation.mbMobileBaseTitleSmallFontWeight
            ),
            labelLargeBold = labelLargeBold.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelLargeBoldFontSize,
                lineHeight = Foundation.mbMobileBaseLabelLargeBoldLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelLargeBoldLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelLargeBoldFontWeight
            ),
            labelLargeRegular = labelLargeRegular.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelLargeRegularFontSize,
                lineHeight = Foundation.mbMobileBaseLabelLargeRegularLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelLargeRegularLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelLargeRegularFontWeight
            ),
            labelMediumBold = labelMediumBold.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelMediumBoldFontSize,
                lineHeight = Foundation.mbMobileBaseLabelMediumBoldLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelMediumBoldLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelMediumBoldFontWeight
            ),
            labelMediumRegular = labelMediumRegular.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelMediumRegularFontSize,
                lineHeight = Foundation.mbMobileBaseLabelMediumRegularLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelMediumRegularLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelMediumRegularFontWeight
            ),
            labelSmallBold = labelSmallBold.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelSmallBoldFontSize,
                lineHeight = Foundation.mbMobileBaseLabelSmallBoldLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelSmallBoldLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelSmallBoldFontWeight
            ),
            labelSmallRegular = labelSmallRegular.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseLabelSmallRegularFontSize,
                lineHeight = Foundation.mbMobileBaseLabelSmallRegularLineHeight,
                letterSpacing = Foundation.mbMobileBaseLabelSmallRegularLetterSpacing,
                fontWeight = Foundation.mbMobileBaseLabelSmallRegularFontWeight
            ),
            paragraphLargeBold = paragraphLargeBold.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseParagraphLargeBoldFontSize,
                lineHeight = Foundation.mbMobileBaseParagraphLargeBoldLineHeight,
                letterSpacing = Foundation.mbMobileBaseParagraphLargeBoldLetterSpacing,
                fontWeight = Foundation.mbMobileBaseParagraphLargeBoldFontWeight
            ),
            paragraphLargeRegular = paragraphLargeRegular.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseParagraphLargeRegularFontSize,
                lineHeight = Foundation.mbMobileBaseParagraphLargeRegularLineHeight,
                letterSpacing = Foundation.mbMobileBaseParagraphLargeRegularLetterSpacing,
                fontWeight = Foundation.mbMobileBaseParagraphLargeRegularFontWeight
            ),
            paragraphSmallBold = paragraphSmallBold.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseParagraphSmallBoldFontSize,
                lineHeight = Foundation.mbMobileBaseParagraphSmallBoldLineHeight,
                letterSpacing = Foundation.mbMobileBaseParagraphSmallBoldLetterSpacing,
                fontWeight = Foundation.mbMobileBaseParagraphSmallBoldFontWeight
            ),
            paragraphSmallRegular = paragraphSmallRegular.copy(
                fontFamily = fontFamily,
                fontSize = Foundation.mbMobileBaseParagraphSmallRegularFontSize,
                lineHeight = Foundation.mbMobileBaseParagraphSmallRegularLineHeight,
                letterSpacing = Foundation.mbMobileBaseParagraphSmallRegularLetterSpacing,
                fontWeight = Foundation.mbMobileBaseParagraphSmallRegularFontWeight
            )
        )
    }
