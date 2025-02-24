package com.fdlr.testemb.coreds.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.fdlr.testemb.coreds.ui.theme.Colors
import com.fdlr.testemb.coreds.ui.theme.Sizing
import com.fdlr.testemb.coreds.ui.theme.Spacing
import com.fdlr.testemb.coreds.ui.theme.Typography
import com.fdlr.testemb.coreds.ui.theme.MBLocalColors
import com.fdlr.testemb.coreds.ui.theme.MBLocalSizing
import com.fdlr.testemb.coreds.ui.theme.MBLocalSpacing
import com.fdlr.testemb.coreds.ui.theme.MBLocalTypography

object MBDesignSystem {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = MBLocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MBLocalTypography.current

    val spacing: Spacing
        @Composable
        @ReadOnlyComposable
        get() = MBLocalSpacing.current

    val sizing: Sizing
        @Composable
        @ReadOnlyComposable
        get() = MBLocalSizing.current
}