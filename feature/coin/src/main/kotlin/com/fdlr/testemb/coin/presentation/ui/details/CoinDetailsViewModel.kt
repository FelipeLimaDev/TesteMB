package com.fdlr.testemb.coin.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdlr.testemb.coin.domain.usecase.GetAssetDetailsUseCase
import com.fdlr.testemb.domain.utils.onError
import com.fdlr.testemb.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CoinDetailsViewModel(
    private val getAssetDetailsUseCase: GetAssetDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinDetailsUiState>(CoinDetailsUiState.Loading)
    val uiState: StateFlow<CoinDetailsUiState> = _uiState.asStateFlow()

    internal fun processIntent(intent: CoinDetailsIntent) {
        viewModelScope.launch {
            when (intent) {
                is CoinDetailsIntent.LoadLocalExchangeRate -> loadLocalExchangeRate(intent.assetId)
                CoinDetailsIntent.OnBack -> _uiState.update { CoinDetailsUiState.OnBack }
            }
        }
    }

    private suspend fun loadLocalExchangeRate(assetId: String) {
        getAssetDetailsUseCase.getLocalExchangeRateByAssetId(assetId)
            .onSuccess { exchangeRate ->
                _uiState.update { CoinDetailsUiState.Success.Details(exchangeRate) }
                getAssetDetails(assetId)
            }
            .onError { error ->
                _uiState.update { CoinDetailsUiState.Error(error) }
            }
    }

    private suspend fun getAssetDetails(assetId: String) {
        getAssetDetailsUseCase.getRemoteExchangeRateByAssetId(assetId)
            .onSuccess { assetDetails ->
                _uiState.update { CoinDetailsUiState.Success.AssetDetails(assetDetails) }
            }
            .onError { error ->
                _uiState.update { CoinDetailsUiState.Error(error) }
            }
    }
}