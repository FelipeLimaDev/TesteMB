package com.fdlr.testemb.coreds.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import com.fdlr.testemb.coreds.ui.theme.schemes.mbLightColors

@Stable
class Colors(
    val background: Color,
    val borderLineGeneral: Color,
    val borderLineInput: Color,
    val error: Color,
    val feedbackInformational: Color,
    val iconDisabled: Color,
    val iconPrimary: Color,
    val negative1: Color,
    val negative2: Color,
    val neutral1: Color,
    val onBodyFeedbackAlert: Color,
    val onBodyFeedbackError: Color,
    val onBodyFeedbackInfo: Color,
    val onBodyFeedbackSuccess: Color,
    val onPrimary: Color,
    val onSurface: Color,
    val opacityOnBody: Color,
    val primary: Color,
    val statesDisabled: Color,
    val surface: Color,
    val surfaceOnBodyBlue: Color,
    val surfaceOnBodyBrand: Color,
    val textDisabled: Color,
    val textPrimary: Color,
    val textSecondary: Color
) {
    fun copy(
        background: Color = this.background,
        borderLineGeneral: Color = this.borderLineGeneral,
        borderLineInput: Color = this.borderLineInput,
        error: Color = this.error,
        feedbackInformational: Color = this.feedbackInformational,
        iconDisabled: Color = this.iconDisabled,
        iconPrimary: Color = this.iconPrimary,
        negative1: Color = this.negative1,
        negative2: Color = this.negative2,
        neutral1: Color = this.neutral1,
        onBodyFeedbackAlert: Color = this.onBodyFeedbackAlert,
        onBodyFeedbackError: Color = this.onBodyFeedbackError,
        onBodyFeedbackInfo: Color = this.onBodyFeedbackInfo,
        onBodyFeedbackSuccess: Color = this.onBodyFeedbackSuccess,
        onPrimary: Color = this.onPrimary,
        onSurface: Color = this.onSurface,
        opacityOnBody: Color = this.opacityOnBody,
        primary: Color = this.primary,
        statesDisabled: Color = this.statesDisabled,
        surface: Color = this.surface,
        surfaceOnBodyBlue: Color = this.surfaceOnBodyBlue,
        surfaceOnBodyBrand: Color = this.surfaceOnBodyBrand,
        textDisabled: Color = this.textDisabled,
        textPrimary: Color = this.textPrimary,
        textSecondary: Color = this.textSecondary
    ): Colors =
        Colors(
            background,
            borderLineGeneral,
            borderLineInput,
            error,
            feedbackInformational,
            iconDisabled,
            iconPrimary,
            negative1,
            negative2,
            neutral1,
            onBodyFeedbackAlert,
            onBodyFeedbackError,
            onBodyFeedbackInfo,
            onBodyFeedbackSuccess,
            onPrimary,
            onSurface,
            opacityOnBody,
            primary,
            statesDisabled,
            surface,
            surfaceOnBodyBlue,
            surfaceOnBodyBrand,
            textDisabled,
            textPrimary,
            textSecondary
        )
}

internal val MBLocalColors = staticCompositionLocalOf { mbLightColors }
