package com.fdlr.testemb.coin.presentation.ui.home

internal sealed interface CoinHomeIntent {
    data object ExitApp : CoinHomeIntent
    data object FetchCoins : CoinHomeIntent
    data object OnSortByPriceClick : CoinHomeIntent
    data object ResetSort : CoinHomeIntent
}