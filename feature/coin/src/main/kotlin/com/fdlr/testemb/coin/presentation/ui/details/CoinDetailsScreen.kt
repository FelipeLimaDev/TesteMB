package com.fdlr.testemb.coin.presentation.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.fdlr.testemb.coin.R
import com.fdlr.testemb.coin.presentation.ui.components.ErrorScreen
import com.fdlr.testemb.coreds.animation.ContainerAnimation
import com.fdlr.testemb.coreds.components.loading.LoadingScreen
import com.fdlr.testemb.coreds.components.scaffold.MBScaffold
import com.fdlr.testemb.coreds.ui.MBDesignSystem
import com.fdlr.testemb.coreds.utils.toDollarCurrency
import com.fdlr.testemb.domain.model.AssetDetails
import com.fdlr.testemb.domain.model.ExchangeRate
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun CoinDetailsScreenRoot(
    assetId: String,
    navController: NavController,
    viewModel: CoinDetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(assetId) {
        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate(assetId))
    }

    LaunchedEffect(uiState) {
        if (uiState is CoinDetailsUiState.OnBack) {
            navController.popBackStack()
        }
    }

    CoinHomeScreen(
        uiState = uiState,
        onIntent = viewModel::processIntent
    )
}

@Composable
private fun CoinHomeScreen(
    uiState: CoinDetailsUiState,
    onIntent: (CoinDetailsIntent) -> Unit
) {
    val localCoinDetails = remember { mutableStateOf<ExchangeRate?>(null) }
    val remoteCoinDetails = remember { mutableStateOf<AssetDetails?>(null) }

    when (uiState) {
        is CoinDetailsUiState.Error -> {
            ErrorScreen(
                error = uiState.error,
                retryButtonText = stringResource(R.string.ok_button_text),
                onDismiss = { onIntent(CoinDetailsIntent.OnBack) }
            )
        }

        CoinDetailsUiState.Loading -> LoadingScreen()

        is CoinDetailsUiState.Success -> {
            when (uiState) {
                is CoinDetailsUiState.Success.Details -> {
                    localCoinDetails.value = uiState.localCoinDetails
                }

                is CoinDetailsUiState.Success.AssetDetails -> {
                    remoteCoinDetails.value = uiState.assetDetails
                }
            }
        }

        else -> Unit
    }

    MBScaffold(
        title = localCoinDetails.value?.assetIdQuote
            ?: stringResource(R.string.title_asset_details),
        onBackNavigate = { onIntent(CoinDetailsIntent.OnBack) }
    ) {
        ContainerAnimation {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MBDesignSystem.spacing.spacing24),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = localCoinDetails.value?.imageUrl.takeIf { !it.isNullOrBlank() }
                            ?: R.drawable.ic_default_cryto,
                        contentDescription = localCoinDetails.value?.assetIdQuote,
                        modifier = Modifier
                            .size(MBDesignSystem.spacing.spacing120)
                            .clip(CircleShape)
                    )

                    remoteCoinDetails.value?.let { asset ->
                        Spacer(modifier = Modifier.size(MBDesignSystem.spacing.spacing20))

                        Column {
                            val typeAsset = when (asset.typeIsCrypto) {
                                1 -> R.string.label_cryptocurrency
                                0 -> R.string.label_currency
                                else -> R.string.label_asset
                            }
                            Text(
                                text = asset.assetName
                                    ?: stringResource(id = R.string.label_unknown),
                                style = MBDesignSystem.typography.titleLarge,
                                color = MBDesignSystem.colors.primary
                            )
                            Text(
                                text = stringResource(typeAsset),
                                style = MBDesignSystem.typography.labelSmallBold,
                                color = MBDesignSystem.colors.textPrimary
                            )

                            Spacer(modifier = Modifier.size(MBDesignSystem.spacing.spacing16))

                            Column {
                                Text(
                                    text = stringResource(
                                        id = R.string.label_price_usd,
                                        asset.priceUsd?.toDollarCurrency()
                                            ?: stringResource(id = R.string.label_unknown)
                                    ),
                                    style = MBDesignSystem.typography.labelSmallBold,
                                    color = MBDesignSystem.colors.textPrimary
                                )
                            }
                        }
                    } ?: run {
                        Spacer(modifier = Modifier.size(MBDesignSystem.spacing.spacing16))
                        Text(
                            text = stringResource(id = R.string.label_loading),
                            style = MBDesignSystem.typography.labelSmallBold,
                            color = MBDesignSystem.colors.textPrimary
                        )
                    }
                }
            }
        }
    }
}
