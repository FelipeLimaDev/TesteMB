package com.fdlr.testemb.coin.presentation.ui.home

import com.fdlr.testemb.domain.model.Coin
import com.fdlr.testemb.domain.utils.DataError

internal sealed class CoinHomeUiState {
    data object Exit : CoinHomeUiState()
    data object Loading : CoinHomeUiState()

    sealed class Success : CoinHomeUiState() {
        data class AllRates(
            val coin: Coin,
            val isFiltering: Boolean = false,
            val isAscending: Boolean = true
        ) : Success()
    }

    data class Error(val error: DataError) : CoinHomeUiState()
}
