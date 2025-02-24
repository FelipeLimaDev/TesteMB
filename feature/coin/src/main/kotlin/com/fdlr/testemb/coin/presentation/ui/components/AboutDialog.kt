package com.fdlr.testemb.coin.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coreds.ui.MBDesignSystem

@Composable
fun AboutDialog(
    show: Boolean,
    onDismiss: () -> Unit
) {
    val textColor = MBDesignSystem.colors.primary

    if (show)
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(usePlatformDefaultWidth = false)
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(MBDesignSystem.spacing.spacing16)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.title_challenge),
                            color = textColor, style = MBDesignSystem.typography.labelLargeBold
                        )
                        Row {
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = onDismiss,
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = MBDesignSystem.colors.primary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close"
                                )
                            }
                        }
                    }

                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing16))
                        Text(
                            text = stringResource(id = R.string.title_tecnologies_and_arch),
                            color = textColor, style = MBDesignSystem.typography.labelMediumBold
                        )
                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing8))
                        Text(
                            text = stringResource(id = R.string.description_tecnologies_and_arch),
                            color = MBDesignSystem.colors.textPrimary
                        )
                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing16))
                        Text(
                            text = stringResource(id = R.string.title_modularization),
                            color = textColor, style = MBDesignSystem.typography.labelMediumBold
                        )
                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing8))
                        Text(
                            text = stringResource(id = R.string.description_modularization),
                            color = MBDesignSystem.colors.textPrimary
                        )

                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing16))
                        Text(
                            text = stringResource(id = R.string.title_tech),
                            color = textColor, style = MBDesignSystem.typography.labelMediumBold
                        )

                        Spacer(modifier = Modifier.height(MBDesignSystem.spacing.spacing8))
                        Text(
                            text = stringResource(id = R.string.description_tech),
                            color = MBDesignSystem.colors.textPrimary
                        )

                    }
                }
            }
        }

}