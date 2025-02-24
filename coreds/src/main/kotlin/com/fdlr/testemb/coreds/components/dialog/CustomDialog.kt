package com.fdlr.testemb.coreds.components.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fdlr.testemb.coreds.R
import com.fdlr.testemb.coreds.ui.MBDesignSystem

@Composable
fun MBDialog(
    type: DialogType,
    title: String,
    description: String,
    buttonText: String?,
    cancelable: Boolean = false,
    onExitApp: (() -> Unit)? = null,
    onDismiss: (() -> Unit)? = null
) {
    val (icon, backgroundColor, textColor) = when (type) {
        DialogType.SUCCESS -> Triple(
            Icons.Default.CheckCircle,
            MBDesignSystem.colors.onBodyFeedbackSuccess,
            MBDesignSystem.colors.onBodyFeedbackSuccess
        )

        DialogType.ERROR -> Triple(
            Icons.Default.Error,
            MBDesignSystem.colors.onBodyFeedbackError,
            MBDesignSystem.colors.onBodyFeedbackError
        )

        DialogType.WARNING -> Triple(
            Icons.Default.Warning,
            MBDesignSystem.colors.primary,
            MBDesignSystem.colors.primary
        )

        DialogType.INFO -> Triple(
            Icons.Default.Info,
            MBDesignSystem.colors.onBodyFeedbackInfo,
            MBDesignSystem.colors.onBodyFeedbackInfo
        )
    }

    Dialog(
        onDismissRequest = { onDismiss?.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = cancelable,
            dismissOnClickOutside = cancelable,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(MBDesignSystem.sizing.sizing16),
            modifier = Modifier.padding(MBDesignSystem.spacing.spacing24),
            elevation = CardDefaults.cardElevation(MBDesignSystem.sizing.sizing8),
            colors = CardDefaults.cardColors(
                containerColor = MBDesignSystem.colors.background
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(MBDesignSystem.spacing.spacing24)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = backgroundColor,
                    modifier = Modifier.size(MBDesignSystem.sizing.sizing68)
                )
                Spacer(modifier = Modifier.height(MBDesignSystem.sizing.sizing8))
                Text(
                    text = title,
                    color = textColor,
                    style = MBDesignSystem.typography.labelMediumRegular
                )
                Spacer(modifier = Modifier.height(MBDesignSystem.sizing.sizing8))
                Text(
                    text = description,
                    textAlign = TextAlign.Center,
                    color = MBDesignSystem.colors.textPrimary
                )
                Spacer(modifier = Modifier.height(MBDesignSystem.sizing.sizing16))
                buttonText?.let {
                    Button(
                        onClick = { onDismiss?.invoke() },
                        colors = ButtonDefaults.buttonColors(backgroundColor)
                    ) {
                        Text(it, color = MBDesignSystem.colors.textPrimary)
                    }
                }

                onExitApp?.let {
                    Spacer(modifier = Modifier.height(MBDesignSystem.sizing.sizing16))
                    Text(
                        modifier = Modifier.clickable { it() },
                        text = stringResource(id = R.string.exit_app),
                        color = MBDesignSystem.colors.textPrimary
                    )
                }
            }
        }
    }
}

enum class DialogType { SUCCESS, ERROR, WARNING, INFO }
