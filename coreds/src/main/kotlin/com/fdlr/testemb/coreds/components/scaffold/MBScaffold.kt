package com.fdlr.testemb.coreds.components.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fdlr.testemb.coreds.components.topbar.Action
import com.fdlr.testemb.coreds.ui.MBDesignSystem
import com.fdlr.testemb.coreds.components.topbar.MBTopBar

@Composable
fun MBScaffold(
    title: String? = null,
    actions: List<Action> = emptyList(),
    onBackNavigate: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        containerColor = MBDesignSystem.colors.background,
        topBar = {
            MBTopBar(
                title = title,
                actions = actions,
                onBackNavigate = onBackNavigate
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MBDesignSystem.colors.background)
        ) {
            content()
        }
    }
}