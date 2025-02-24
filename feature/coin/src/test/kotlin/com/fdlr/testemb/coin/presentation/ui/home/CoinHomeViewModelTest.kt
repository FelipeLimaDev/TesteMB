package com.fdlr.testemb.coin.presentation.ui.home

import app.cash.turbine.test
import com.fdlr.testemb.coin.domain.usecase.GetAssetIconsUseCase
import com.fdlr.testemb.coin.domain.usecase.GetExchangeRatesUseCase
import com.fdlr.testemb.coin.utils.CoroutinesTestRule
import com.fdlr.testemb.coin.utils.TestUtils
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoinHomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getAssetIconsUseCase: GetAssetIconsUseCase = mockk(relaxUnitFun = true)
    private val getExchangeRatesUseCase: GetExchangeRatesUseCase = mockk(relaxUnitFun = true)

    private lateinit var viewModel: CoinHomeViewModel

    @Before
    fun setUp() {
        viewModel = CoinHomeViewModel(getAssetIconsUseCase, getExchangeRatesUseCase)
    }

    @Test
    fun `Emitir Success quando getExchangeRatesUseCase retorna sucesso`() = runTest {
        coEvery { getExchangeRatesUseCase() } returns Result.Success(TestUtils.sampleCoin)
        viewModel.uiState.test {
            val successState = awaitItem() as CoinHomeUiState.Success.AllRates
            assertEquals(TestUtils.sampleCoin, successState.coin)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Deve emitir erro quando use case falha`() = runTest {
        coEvery { getExchangeRatesUseCase() } returns Result.Error(DataError.Local.UNKNOWN)
        viewModel.uiState.test {
            val errorState = awaitItem() as CoinHomeUiState.Error
            assertEquals(DataError.Local.UNKNOWN, errorState.error)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Sort por preco deve inverter ordem corretamente`() = runTest {
        coEvery { getExchangeRatesUseCase() } returns Result.Success(TestUtils.sampleCoin)
        viewModel.uiState.test {
            val initialState = awaitItem() as CoinHomeUiState.Success.AllRates
            viewModel.processIntent(CoinHomeIntent.OnSortByPriceClick)
            val sortedState = awaitItem() as CoinHomeUiState.Success.AllRates
            assertEquals(!initialState.isAscending, sortedState.isAscending)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `ResetSort deve restaurar lista original`() = runTest {
        coEvery { getExchangeRatesUseCase() } returns Result.Success(TestUtils.sampleCoin)
        viewModel.uiState.test {
            viewModel.processIntent(CoinHomeIntent.ResetSort)
            val resetState = awaitItem() as CoinHomeUiState.Success.AllRates
            assertEquals(TestUtils.sampleCoin, resetState.coin)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `RefreshCoins deve chamar getExchangeRates`() = runTest {
        coEvery { getExchangeRatesUseCase() } returns Result.Success(TestUtils.sampleCoin)
        viewModel.processIntent(CoinHomeIntent.FetchCoins)
        coVerify(exactly = 1) { getExchangeRatesUseCase() }
    }
}