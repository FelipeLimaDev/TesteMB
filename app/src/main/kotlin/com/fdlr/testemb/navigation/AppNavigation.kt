package com.fdlr.testemb.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fdlr.testemb.coin.presentation.navigation.coinGraph
import com.fdlr.testemb.coin.presentation.navigation.navigateToCoinGraph
import kotlinx.serialization.Serializable

@Composable
fun AppNavigation(
    isDarkMode: Boolean,
    onDarkModeChange: (Boolean) -> Unit,
    onFinished: () -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.CoinModuleRoute,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        composable<AppRoute.CoinModuleRoute> {
            navController.navigateToCoinGraph()
        }

        coinGraph(
            navController = navController,
            isDarkMode = isDarkMode,
            onDarkModeChange = onDarkModeChange,
            onFinished = onFinished
        )
    }
}

sealed interface AppRoute {
    @Serializable
    data object CoinModuleRoute : AppRoute
}