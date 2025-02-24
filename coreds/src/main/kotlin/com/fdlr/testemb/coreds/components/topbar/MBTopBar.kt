package com.fdlr.testemb.coreds.components.topbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.fdlr.testemb.coreds.ui.MBDesignSystem

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun MBTopBar(
    title: String?,
    actions: List<Action>,
    onBackNavigate: (() -> Unit)?
) {
    TopAppBar(
        title = {
            title?.let {
                Text(
                    it,
                    color = MBDesignSystem.colors.iconPrimary,
                    style = MBDesignSystem.typography.labelMediumRegular
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MBDesignSystem.colors.neutral1,
            actionIconContentColor = MBDesignSystem.colors.iconPrimary,
            navigationIconContentColor = MBDesignSystem.colors.iconPrimary
        ),
        actions = {
            Row {
                actions.forEach { action ->
                    IconButton(onClick = action.onClick) {
                        action.icon()
                    }
                }
            }
        },
        navigationIcon = {
            onBackNavigate?.let {
                IconButton(onClick = it) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        }
    )
}

data class Action(
    val icon: @Composable () -> Unit,
    val onClick: () -> Unit
)