package com.fdlr.testemb.coin.presentation.ui.details

import com.fdlr.testemb.domain.model.ExchangeRate
import com.fdlr.testemb.domain.utils.DataError

internal sealed class CoinDetailsUiState {
    data object Loading : CoinDetailsUiState()
    data object OnBack : CoinDetailsUiState()
    sealed class Success : CoinDetailsUiState() {
        data class Details(val localCoinDetails: ExchangeRate) : Success()
        data class AssetDetails(val assetDetails: com.fdlr.testemb.domain.model.AssetDetails) : Success()
    }

    data class Error(val error: DataError) : CoinDetailsUiState()
}