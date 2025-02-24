package com.fdlr.testemb.coin.presentation.ui.home

import androidx.activity.compose.BackHandler
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coin.presentation.navigation.CoinRoute
import com.fdlr.testemb.coin.presentation.ui.components.AboutDialog
import com.fdlr.testemb.coin.presentation.ui.components.ErrorScreen
import com.fdlr.testemb.coin.presentation.ui.details.CoinDetailsUiState
import com.fdlr.testemb.coin.presentation.ui.home.components.CoinItem
import com.fdlr.testemb.coin.presentation.ui.home.components.Header
import com.fdlr.testemb.coreds.components.loading.LoadingScreen
import com.fdlr.testemb.coreds.components.scaffold.MBScaffold
import com.fdlr.testemb.coreds.components.topbar.Action
import com.fdlr.testemb.domain.model.Rate
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CoinHomeScreenRoot(
    navController: NavController,
    viewModel: CoinHomeViewModel = koinViewModel(),
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onFinished: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is CoinHomeUiState.Exit) {
            onFinished()
        }
    }

    CoinHomeScreen(
        uiState = uiState,
        navController = navController,
        isDarkMode = isDarkMode,
        onDarkModeChange = onDarkModeChange,
        onIntent = viewModel::processIntent
    )

    BackHandler(true) {
        onFinished()
    }
}

@VisibleForTesting
@Composable
private fun CoinHomeScreen(
    uiState: CoinHomeUiState,
    navController: NavController,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onIntent: (CoinHomeIntent) -> Unit
) {
    var aboutDialogOpen by remember { mutableStateOf(false) }
    val coins = remember { mutableStateOf<List<Rate>>(emptyList()) }

    when (uiState) {
        is CoinHomeUiState.Loading -> LoadingScreen()
        is CoinHomeUiState.Success -> {
            when (uiState) {
                is CoinHomeUiState.Success.AllRates -> {
                    coins.value = uiState.coin.rates
                }
            }
        }

        is CoinHomeUiState.Error -> {
            ErrorScreen(
                error = uiState.error,
                onExitApp = { onIntent(CoinHomeIntent.ExitApp) },
                onDismiss = { onIntent(CoinHomeIntent.FetchCoins) })
        }

        else -> Unit
    }
    AboutDialog(show = aboutDialogOpen) {
        aboutDialogOpen = false
    }

    MBScaffold(
        title = stringResource(id = R.string.title_test_mb),
        actions = listOf(
            Action(
                icon = {
                    Icon(
                        if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode,
                        contentDescription = null
                    )
                },
                onClick = { onDarkModeChange(!isDarkMode) }
            ),
            Action(
                icon = { Icon(Icons.Filled.Info, contentDescription = null) },
                onClick = { aboutDialogOpen = true }
            )
        )
    ) {
        Header(onIntent, uiState)
        LazyColumn {
            itemsIndexed(coins.value) { index, rate ->
                CoinItem(
                    index = index,
                    rate = rate,
                    onClick = { navController.navigate(CoinRoute.CoinDetails(rate.assetIdQuote)) })
            }
        }
    }
}

