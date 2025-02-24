package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.coin.utils.TestUtils
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

class GetExchangeRatesUseCaseTest {

    private val repository: CoinRepository = mockk()
    private lateinit var useCase: GetExchangeRatesUseCase

    @Before
    fun setUp() {
        useCase = GetExchangeRatesUseCase(repository)
    }

    @Test
    fun `invoke - sucesso retorna CoinDomain`() = runBlocking {
        val mockCoinDomain = TestUtils.sampleCoin
        coEvery { repository.getExchangeRates(assetIdBase = "BRL") } returns Result.Success(
            mockCoinDomain
        )

        val result = useCase("BRL")

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals("BTC", data.assetIdBase)
    }

    @Test
    fun `invoke - erro retorna ResultError`() = runBlocking {
        coEvery { repository.getExchangeRates(assetIdBase = "BRL") } returns Result.Error(DataError.Remote.NOT_FOUND)

        val result = useCase("BRL")
        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertEquals(DataError.Remote.NOT_FOUND, error)
    }
}
