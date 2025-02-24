package com.fdlr.testemb.coreds.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.fdlr.testemb.coreds.ui.theme.schemes.mbDarkColors
import com.fdlr.testemb.coreds.ui.theme.schemes.mbLightColors
import com.fdlr.testemb.coreds.ui.theme.schemes.mbSizing
import com.fdlr.testemb.coreds.ui.theme.schemes.mbSpacing
import com.fdlr.testemb.coreds.ui.theme.schemes.mbTypography

@Composable
fun TesteMBTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    InternalTheme(
        colors = if (isDarkTheme) mbDarkColors else mbLightColors,
        typography = mbTypography,
        spacing = mbSpacing,
        sizing = mbSizing,
        content = content
    )
}

@Composable
private fun InternalTheme(
    colors: Colors,
    typography: Typography,
    spacing: Spacing,
    sizing: Sizing,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        MBLocalColors provides colors,
        MBLocalTypography provides typography,
        MBLocalSpacing provides spacing,
        MBLocalSizing provides sizing
    ) {
        content()
    }
}
