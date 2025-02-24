package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.coin.utils.TestUtils
import com.fdlr.testemb.domain.model.ExchangeRate
import com.fdlr.testemb.domain.repository.CoinRepository
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class GetAssetDetailsUseCaseTest {

    private val coinRepository: CoinRepository = mockk()
    private lateinit var useCase: GetAssetDetailsUseCase

    @Before
    fun setUp() {
        useCase = GetAssetDetailsUseCase(coinRepository)
    }

    @Test
    fun `getRemoteExchangeRateByAssetId - retorna sucesso quando repositorio retorna sucesso`() =
        runBlocking {
            val mockAssetDetails = TestUtils.sampleAssetDetails

            coEvery { coinRepository.getAssetDetails(assetIdQuote = "BTC") } returns Result.Success(
                mockAssetDetails
            )

            val result = useCase.getRemoteExchangeRateByAssetId(assetIdQuote = "BTC")

            assertTrue(result is Result.Success)
            val data = (result as Result.Success).data
            assertEquals("BTC", data.assetId)
        }

    @Test
    fun `getRemoteExchangeRateByAssetId - retorna erro quando repositorio retorna erro`() =
        runBlocking {

            coEvery { coinRepository.getAssetDetails(assetIdQuote = "BTC") } returns Result.Error(
                DataError.Remote.NOT_FOUND
            )

            val result = useCase.getRemoteExchangeRateByAssetId(assetIdQuote = "BTC")
            assertTrue(result is Result.Error)
            val error = (result as Result.Error).error
            assertEquals(DataError.Remote.NOT_FOUND, error)
        }

    @Test
    fun `getLocalExchangeRateByAssetId - retorna sucesso quando repositorio retorna sucesso`() =
        runBlocking {
            val mockExchangeRate = ExchangeRate(
                time = "2025-01-01T12:00:00",
                assetIdBase = "BTC",
                assetIdQuote = "ETH",
                rate = 0.07,
                imageUrl = "url"
            )
            coEvery { coinRepository.getLocalExchangeRateByAssetId(assetIdQuote = "ETH") } returns Result.Success(
                mockExchangeRate
            )

            val result = useCase.getLocalExchangeRateByAssetId(assetIdQuote = "ETH")

            assertTrue(result is Result.Success)
            val data = (result as Result.Success).data
            assertEquals("ETH", data.assetIdQuote)
        }

    @Test
    fun `getLocalExchangeRateByAssetId - retorna erro quando repositorio retorna erro`() =
        runBlocking {
            coEvery { coinRepository.getLocalExchangeRateByAssetId(assetIdQuote = "ETH") } returns Result.Error(
                DataError.Local.UNKNOWN
            )

            val result = useCase.getLocalExchangeRateByAssetId(assetIdQuote = "ETH")
            assertTrue(result is Result.Error)
            val error = (result as Result.Error).error
            assertEquals(DataError.Local.UNKNOWN, error)
        }
}
