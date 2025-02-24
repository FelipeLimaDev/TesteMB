package com.fdlr.testemb.coreds.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.fdlr.testemb.coreds.R

@Composable
internal fun AppFontFamily() = FontFamily(
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_bold, weight = FontWeight.Bold)
)

@Immutable
class Typography internal constructor(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val labelLargeBold: TextStyle,
    val labelLargeRegular: TextStyle,
    val labelMediumBold: TextStyle,
    val labelMediumRegular: TextStyle,
    val labelSmallBold: TextStyle,
    val labelSmallRegular: TextStyle,
    val paragraphLargeBold: TextStyle,
    val paragraphLargeRegular: TextStyle,
    val paragraphSmallBold: TextStyle,
    val paragraphSmallRegular: TextStyle
) {
    constructor(
        defaultFontFamily: FontFamily = FontFamily.Default,
        displayLarge: TextStyle = TextStyle(),
        displayMedium: TextStyle = TextStyle(),
        displaySmall: TextStyle = TextStyle(),
        titleLarge: TextStyle = TextStyle(),
        titleMedium: TextStyle = TextStyle(),
        titleSmall: TextStyle = TextStyle(),
        labelLargeBold: TextStyle = TextStyle(),
        labelLargeRegular: TextStyle = TextStyle(),
        labelMediumBold: TextStyle = TextStyle(),
        labelMediumRegular: TextStyle = TextStyle(),
        labelSmallBold: TextStyle = TextStyle(),
        labelSmallRegular: TextStyle = TextStyle(),
        paragraphLargeBold: TextStyle = TextStyle(),
        paragraphLargeRegular: TextStyle = TextStyle(),
        paragraphSmallBold: TextStyle = TextStyle(),
        paragraphSmallRegular: TextStyle = TextStyle()
    ) : this(
        displayLarge = displayLarge.withDefaultFontFamily(defaultFontFamily),
        displayMedium = displayMedium.withDefaultFontFamily(defaultFontFamily),
        displaySmall = displaySmall.withDefaultFontFamily(defaultFontFamily),
        titleLarge = titleLarge.withDefaultFontFamily(defaultFontFamily),
        titleMedium = titleMedium.withDefaultFontFamily(defaultFontFamily),
        titleSmall = titleSmall.withDefaultFontFamily(defaultFontFamily),
        labelLargeBold = labelLargeBold.withDefaultFontFamily(defaultFontFamily),
        labelLargeRegular = labelLargeRegular.withDefaultFontFamily(defaultFontFamily),
        labelMediumBold = labelMediumBold.withDefaultFontFamily(defaultFontFamily),
        labelMediumRegular = labelMediumRegular.withDefaultFontFamily(defaultFontFamily),
        labelSmallBold = labelSmallBold.withDefaultFontFamily(defaultFontFamily),
        labelSmallRegular = labelSmallRegular.withDefaultFontFamily(defaultFontFamily),
        paragraphLargeBold = paragraphLargeBold.withDefaultFontFamily(defaultFontFamily),
        paragraphLargeRegular = paragraphLargeRegular.withDefaultFontFamily(defaultFontFamily),
        paragraphSmallBold = paragraphSmallBold.withDefaultFontFamily(defaultFontFamily),
        paragraphSmallRegular = paragraphSmallRegular.withDefaultFontFamily(defaultFontFamily)
    )

    fun copy(
        displayLarge: TextStyle = this.displayLarge,
        displayMedium: TextStyle = this.displayMedium,
        displaySmall: TextStyle = this.displaySmall,
        titleLarge: TextStyle = this.titleLarge,
        titleMedium: TextStyle = this.titleMedium,
        titleSmall: TextStyle = this.titleSmall,
        labelLargeBold: TextStyle = this.labelLargeBold,
        labelLargeRegular: TextStyle = this.labelLargeRegular,
        labelMediumBold: TextStyle = this.labelMediumBold,
        labelMediumRegular: TextStyle = this.labelMediumRegular,
        labelSmallBold: TextStyle = this.labelSmallBold,
        labelSmallRegular: TextStyle = this.labelSmallRegular,
        paragraphLargeBold: TextStyle = this.paragraphLargeBold,
        paragraphLargeRegular: TextStyle = this.paragraphLargeRegular,
        paragraphSmallBold: TextStyle = this.paragraphSmallBold,
        paragraphSmallRegular: TextStyle = this.paragraphSmallRegular
    ): Typography =
        Typography(
            displayLarge = displayLarge,
            displayMedium = displayMedium,
            displaySmall = displaySmall,
            titleLarge = titleLarge,
            titleMedium = titleMedium,
            titleSmall = titleSmall,
            labelLargeBold = labelLargeBold,
            labelLargeRegular = labelLargeRegular,
            labelMediumBold = labelMediumBold,
            labelMediumRegular = labelMediumRegular,
            labelSmallBold = labelSmallBold,
            labelSmallRegular = labelSmallRegular,
            paragraphLargeBold = paragraphLargeBold,
            paragraphLargeRegular = paragraphLargeRegular,
            paragraphSmallBold = paragraphSmallBold,
            paragraphSmallRegular = paragraphSmallRegular
        )
}

private fun TextStyle.withDefaultFontFamily(default: FontFamily): TextStyle =
    if (fontFamily != null) this else copy(fontFamily = default)

internal val MBLocalTypography = staticCompositionLocalOf { Typography() }
