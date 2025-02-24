package com.fdlr.testemb.coreds.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import com.fdlr.testemb.coreds.ui.theme.schemes.mbSizing

@Stable
class Sizing(
    val sizing120: Dp,
    val sizing96: Dp,
    val sizing80: Dp,
    val sizing68: Dp,
    val sizing64: Dp,
    val sizing56: Dp,
    val sizing52: Dp,
    val sizing48: Dp,
    val sizing40: Dp,
    val sizing36: Dp,
    val sizing32: Dp,
    val sizing28: Dp,
    val sizing24: Dp,
    val sizing20: Dp,
    val sizing16: Dp,
    val sizing14: Dp,
    val sizing12: Dp,
    val sizing8: Dp,
    val sizing4: Dp,
    val sizing1: Dp
) {
    fun copy(
        sizing120: Dp = this.sizing120,
        sizing96: Dp = this.sizing96,
        sizing80: Dp = this.sizing80,
        sizing68: Dp = this.sizing68,
        sizing64: Dp = this.sizing64,
        sizing56: Dp = this.sizing56,
        sizing52: Dp = this.sizing52,
        sizing48: Dp = this.sizing48,
        sizing40: Dp = this.sizing40,
        sizing36: Dp = this.sizing36,
        sizing32: Dp = this.sizing32,
        sizing28: Dp = this.sizing28,
        sizing24: Dp = this.sizing24,
        sizing20: Dp = this.sizing20,
        sizing16: Dp = this.sizing16,
        sizing14: Dp = this.sizing14,
        sizing12: Dp = this.sizing12,
        sizing8: Dp = this.sizing8,
        sizing4: Dp = this.sizing4,
        sizing1: Dp = this.sizing1
    ): Sizing =
        Sizing(
            sizing120,
            sizing96,
            sizing80,
            sizing68,
            sizing64,
            sizing56,
            sizing52,
            sizing48,
            sizing40,
            sizing36,
            sizing32,
            sizing28,
            sizing24,
            sizing20,
            sizing16,
            sizing14,
            sizing12,
            sizing8,
            sizing4,
            sizing1
        )
}

internal val MBLocalSizing = staticCompositionLocalOf { mbSizing }