package com.fdlr.testemb.coin.presentation.ui.details

internal sealed interface CoinDetailsIntent {
    data object OnBack: CoinDetailsIntent
    data class LoadLocalExchangeRate(val assetId: String) : CoinDetailsIntent
}