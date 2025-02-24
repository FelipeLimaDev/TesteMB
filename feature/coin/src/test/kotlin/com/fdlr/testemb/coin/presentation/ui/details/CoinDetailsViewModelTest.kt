package com.fdlr.testemb.coin.presentation.ui.details

import app.cash.turbine.test
import com.fdlr.testemb.coin.domain.usecase.GetAssetDetailsUseCase
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
class CoinDetailsViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val getAssetDetailsUseCase: GetAssetDetailsUseCase = mockk(relaxUnitFun = true)
    private lateinit var viewModel: CoinDetailsViewModel

    @Before
    fun setUp() {
        viewModel = CoinDetailsViewModel(getAssetDetailsUseCase)
    }

    @Test
    fun `Emitir Success quando getLocalExchangeRateByAssetId retorna sucesso`() = runTest {
        coEvery { getAssetDetailsUseCase.getLocalExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleExchangeRate)
        coEvery { getAssetDetailsUseCase.getRemoteExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleAssetDetails)

        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate("BTC"))

        viewModel.uiState.test {
            val assetDetailsState = awaitItem() as CoinDetailsUiState.Success.AssetDetails
            assertEquals(TestUtils.sampleAssetDetails, assetDetailsState.assetDetails)

            cancelAndConsumeRemainingEvents()
        }
    }


    @Test
    fun `Deve emitir erro quando getLocalExchangeRateByAssetId falha`() = runTest {
        coEvery { getAssetDetailsUseCase.getLocalExchangeRateByAssetId("BTC") } returns Result.Error(DataError.Local.UNKNOWN)

        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate("BTC"))

        viewModel.uiState.test {
            val errorState = awaitItem() as CoinDetailsUiState.Error
            assertEquals(DataError.Local.UNKNOWN, errorState.error)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Deve chamar getRemoteExchangeRateByAssetId apos sucesso no getLocalExchangeRateByAssetId`() = runTest {
        coEvery { getAssetDetailsUseCase.getLocalExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleExchangeRate)
        coEvery { getAssetDetailsUseCase.getRemoteExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleAssetDetails)

        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate("BTC"))

        coVerify(exactly = 1) { getAssetDetailsUseCase.getRemoteExchangeRateByAssetId("BTC") }
    }

    @Test
    fun `Deve emitir Success AssetDetails quando getRemoteExchangeRateByAssetId retorna sucesso`() = runTest {
        coEvery { getAssetDetailsUseCase.getLocalExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleExchangeRate)
        coEvery { getAssetDetailsUseCase.getRemoteExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleAssetDetails)

        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate("BTC"))

        viewModel.uiState.test {
            val assetDetailsState = awaitItem() as CoinDetailsUiState.Success.AssetDetails
            assertEquals(TestUtils.sampleAssetDetails, assetDetailsState.assetDetails)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Deve emitir erro quando getRemoteExchangeRateByAssetId falha`() = runTest {
        coEvery { getAssetDetailsUseCase.getLocalExchangeRateByAssetId("BTC") } returns Result.Success(TestUtils.sampleExchangeRate)
        coEvery { getAssetDetailsUseCase.getRemoteExchangeRateByAssetId("BTC") } returns Result.Error(DataError.Local.UNKNOWN)

        viewModel.processIntent(CoinDetailsIntent.LoadLocalExchangeRate("BTC"))

        viewModel.uiState.test {
            val errorState = awaitItem() as CoinDetailsUiState.Error
            assertEquals(DataError.Local.UNKNOWN, errorState.error)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `Deve emitir OnBack quando processa OnBack`() = runTest {
        viewModel.processIntent(CoinDetailsIntent.OnBack)
        viewModel.uiState.test {
            val backState = awaitItem()
            assertEquals(CoinDetailsUiState.OnBack, backState)
            cancelAndConsumeRemainingEvents()
        }
    }
}
