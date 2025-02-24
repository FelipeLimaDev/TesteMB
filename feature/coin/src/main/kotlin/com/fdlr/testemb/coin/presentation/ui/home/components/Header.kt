package com.fdlr.testemb.coin.presentation.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coin.presentation.ui.home.CoinHomeIntent
import com.fdlr.testemb.coin.presentation.ui.home.CoinHomeUiState
import com.fdlr.testemb.coreds.ui.MBDesignSystem

@Composable
internal fun Header(
    onIntent: (CoinHomeIntent) -> Unit,
    uiState: CoinHomeUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MBDesignSystem.colors.neutral1)
            .padding(
                horizontal = MBDesignSystem.spacing.spacing16,
                vertical = MBDesignSystem.spacing.spacing8
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#", modifier = Modifier.weight(1f),
            style = MBDesignSystem.typography.labelSmallRegular,
            color = MBDesignSystem.colors.iconPrimary
        )
        Text(
            text = stringResource(R.string.label_crypto),
            modifier = Modifier.weight(4f),
            style = MBDesignSystem.typography.labelSmallRegular,
            color = MBDesignSystem.colors.iconPrimary
        )
        Row(
            modifier = Modifier
                .weight(4f)
                .clickable {
                    onIntent(CoinHomeIntent.OnSortByPriceClick)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.label_price),
                style = MBDesignSystem.typography.labelSmallRegular,
                color = MBDesignSystem.colors.iconPrimary
            )
            if (uiState is CoinHomeUiState.Success.AllRates && uiState.isFiltering) {
                val arrow = if (uiState.isAscending) {
                    Icons.Filled.KeyboardArrowUp
                } else {
                    Icons.Filled.KeyboardArrowDown
                }
                Icon(
                    arrow, contentDescription = null,
                    tint = MBDesignSystem.colors.primary
                )
            }
        }

        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (uiState is CoinHomeUiState.Success.AllRates && uiState.isFiltering) {
                IconButton(
                    modifier = Modifier.size(MBDesignSystem.sizing.sizing28),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MBDesignSystem.colors.primary
                    ),
                    onClick = { onIntent(CoinHomeIntent.ResetSort) }) {
                    Icon(
                        Icons.Filled.RestartAlt,
                        contentDescription = null
                    )
                }
            } else {
                IconButton(
                    modifier = Modifier.size(MBDesignSystem.sizing.sizing28),
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MBDesignSystem.colors.iconPrimary
                    ),
                    onClick = { onIntent(CoinHomeIntent.OnSortByPriceClick) }) {
                    Icon(
                        Icons.AutoMirrored.Filled.Sort,
                        contentDescription = null
                    )
                }
            }
        }

    }
}