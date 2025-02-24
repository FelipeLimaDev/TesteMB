package com.fdlr.testemb.coin.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdlr.testemb.coin.domain.usecase.GetAssetIconsUseCase
import com.fdlr.testemb.coin.domain.usecase.GetExchangeRatesUseCase
import com.fdlr.testemb.domain.utils.Result
import com.fdlr.testemb.domain.utils.onError
import com.fdlr.testemb.domain.utils.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CoinHomeViewModel(
    private val getAssetIconsUseCase: GetAssetIconsUseCase,
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CoinHomeUiState>(CoinHomeUiState.Loading)
    val uiState: StateFlow<CoinHomeUiState> = _uiState
        .onStart {
            getExchangeRates()
            getAssetIconsUseCase()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _uiState.value
        )

    internal fun processIntent(intent: CoinHomeIntent) {
        viewModelScope.launch {
            when (intent) {
                CoinHomeIntent.FetchCoins -> getExchangeRates()
                CoinHomeIntent.OnSortByPriceClick -> sortByPrice()
                CoinHomeIntent.ResetSort -> resetSort()
                CoinHomeIntent.ExitApp -> _uiState.value = CoinHomeUiState.Exit
            }
        }
    }

    private fun sortByPrice() {
        _uiState.update { current ->
            if (current is CoinHomeUiState.Success.AllRates) {
                val sortedList = if (current.isAscending) {
                    current.coin.rates.sortedByDescending { it.rate }
                } else {
                    current.coin.rates.sortedBy { it.rate }
                }

                current.copy(
                    coin = current.coin.copy(rates = sortedList),
                    isFiltering = true,
                    isAscending = !current.isAscending
                )
            } else {
                current
            }
        }
    }

    private suspend fun resetSort() {
        _uiState.update { current ->
            if (current is CoinHomeUiState.Success.AllRates) {
                current.copy(
                    coin = getExchangeRatesUseCase().let {
                        if (it is Result.Success) it.data else current.coin
                    },
                    isFiltering = false
                )
            } else {
                current
            }
        }
    }

    private suspend fun getExchangeRates() {
        getExchangeRatesUseCase()
            .onSuccess { result ->
                _uiState.value = CoinHomeUiState.Success.AllRates(result)
            }
            .onError { error ->
                _uiState.value = CoinHomeUiState.Error(error)
            }
    }
}