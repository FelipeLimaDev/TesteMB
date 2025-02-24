package com.fdlr.testemb.coin.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.fdlr.testemb.coin.presentation.ui.details.CoinDetailsScreenRoot
import com.fdlr.testemb.coin.presentation.ui.home.CoinHomeScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.coinGraph(
    navController: NavHostController,
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onFinished: () -> Unit
) {
    navigation<CoinRoute.CoinNavigator>(
        startDestination = CoinRoute.CoinHome
    ) {
        composable<CoinRoute.CoinHome> {
            CoinHomeScreenRoot(
                navController = navController,
                onFinished = onFinished,
                isDarkMode = isDarkMode,
                onDarkModeChange = onDarkModeChange
            )
        }

        composable<CoinRoute.CoinDetails> {
            val args = it.toRoute<CoinRoute.CoinDetails>()
            CoinDetailsScreenRoot(
                assetId = args.assetId,
                navController = navController
            )
        }

    }
}

fun NavController.navigateToCoinGraph() {
    this.navigate(CoinRoute.CoinHome)
}

sealed interface CoinRoute {
    @Serializable
    data object CoinNavigator : CoinRoute

    @Serializable
    data object CoinHome : CoinRoute

    @Serializable
    data class CoinDetails(
        val assetId: String
    ) : CoinRoute
}
