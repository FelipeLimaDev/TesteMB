package com.fdlr.testemb.coreds.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import com.fdlr.testemb.coreds.ui.theme.schemes.mbSpacing

@Stable
class Spacing(
    val screenPadding: Dp,
    val buttonTextPadding: Dp,
    val iconSize: Dp,
    val spacing120: Dp,
    val spacing96: Dp,
    val spacing80: Dp,
    val spacing64: Dp,
    val spacing56: Dp,
    val spacing48: Dp,
    val spacing40: Dp,
    val spacing32: Dp,
    val spacing24: Dp,
    val spacing20: Dp,
    val spacing16: Dp,
    val spacing12: Dp,
    val spacing8: Dp,
    val spacing4: Dp,
    val spacing2: Dp,
    val spacing1: Dp
) {
    fun copy(
        screenPadding: Dp = this.screenPadding,
        buttonTextPadding: Dp = this.buttonTextPadding,
        iconSize: Dp = this.iconSize,
        spacing120: Dp = this.spacing120,
        spacing96: Dp = this.spacing96,
        spacing80: Dp = this.spacing80,
        spacing64: Dp = this.spacing64,
        spacing56: Dp = this.spacing56,
        spacing48: Dp = this.spacing48,
        spacing40: Dp = this.spacing40,
        spacing32: Dp = this.spacing32,
        spacing24: Dp = this.spacing24,
        spacing20: Dp = this.spacing20,
        spacing16: Dp = this.spacing16,
        spacing12: Dp = this.spacing12,
        spacing8: Dp = this.spacing8,
        spacing4: Dp = this.spacing4,
        spacing2: Dp = this.spacing2,
        spacing1: Dp = this.spacing1
    ): Spacing =
        Spacing(
            screenPadding,
            buttonTextPadding,
            iconSize,
            spacing120,
            spacing96,
            spacing80,
            spacing64,
            spacing56,
            spacing48,
            spacing40,
            spacing32,
            spacing24,
            spacing20,
            spacing16,
            spacing12,
            spacing8,
            spacing4,
            spacing2,
            spacing1
        )
}

internal val MBLocalSpacing = staticCompositionLocalOf { mbSpacing }
