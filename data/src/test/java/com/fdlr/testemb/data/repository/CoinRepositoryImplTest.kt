package com.fdlr.testemb.data.repository

import com.fdlr.testemb.data.exceptions.DataErrorNotFoundException
import com.fdlr.testemb.data.source.local.dao.AssetDao
import com.fdlr.testemb.data.source.local.dao.ExchangeRateDao
import com.fdlr.testemb.data.source.remote.api.CoinApi
import com.fdlr.testemb.data.utils.TestUtils
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CoinRepositoryTest {

    private val coinApi: CoinApi = mockk()
    private val assetDao: AssetDao = mockk()
    private val exchangeRateDao: ExchangeRateDao = mockk()

    private lateinit var repository: CoinRepositoryImpl

    @Before
    fun setUp() {
        repository = CoinRepositoryImpl(
            coinApi = coinApi,
            assetDao = assetDao,
            exchangeRateDao = exchangeRateDao
        )
    }

    @Test
    fun `getExchangeRates - deve retornar erro se falhar ao recuperar dados do banco`() = runBlocking {
        coEvery { exchangeRateDao.getAllExchangeRates() } throws DataErrorNotFoundException()

        val result = repository.getExchangeRates("USD")

        assertTrue(result is Result.Error)
    }


    @Test
    fun `getLocalExchangeRateByAssetId - deve retornar sucesso se existir registro no banco`() =
        runBlocking {
            coEvery { exchangeRateDao.getExchangeRateByAssetId("ETH") } returns TestUtils.sampleExchangeRateEntity

            val result = repository.getLocalExchangeRateByAssetId("ETH")

            assertTrue(result is Result.Success)
            val data = (result as Result.Success).data
            assertEquals("ETH", data.assetIdQuote)
        }

    @Test
    fun `getLocalExchangeRateByAssetId - deve retornar erro se nao encontrar registro`() =
        runBlocking {
            coEvery { exchangeRateDao.getExchangeRateByAssetId("ETH") } returns null

            val result = repository.getLocalExchangeRateByAssetId("ETH")

            assertTrue(result is Result.Error)
            val error = (result as Result.Error).error
            assertTrue(error is DataError.Local)
        }

    @Test
    fun `getSpecificExchangeRate - deve retornar valor da API se tiver sucesso`() = runBlocking {
        coEvery {
            coinApi.getSpecificExchangeRate(
                "BTC",
                "ETH"
            )
        } returns TestUtils.sampleExchangeRateDto

        val result = repository.getSpecificExchangeRate(assetIdBase = "BTC", assetIdQuote = "ETH")

        assertTrue(result is Result.Success)
        val domain = (result as Result.Success).data
        assertEquals("BTC", domain.assetIdBase)
        assertEquals("ETH", domain.assetIdQuote)
        assertEquals(TestUtils.sampleExchangeRateDto.rate, domain.rate)
    }

    @Test
    fun `getAssets - deve retornar lista da API`() = runBlocking {
        coEvery { coinApi.getAssets() } returns listOf(TestUtils.sampleAssetDto)

        val result = repository.getAssets()
        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals(1, data.size)
        assertEquals("BTC", data[0].assetId)
    }

    @Test
    fun `getAssetDetails - deve retornar primeiro item da lista se sucesso na API`() = runBlocking {
        coEvery { coinApi.getAssetDetails("BTC") } returns listOf(TestUtils.sampleAssetDetailsDto)

        val result = repository.getAssetDetails(assetIdQuote = "BTC")

        assertTrue(result is Result.Success)
        val data = (result as Result.Success).data
        assertEquals("BTC", data.assetId)
        assertEquals("Bitcoin", data.assetName)
    }

    @Test
    fun `getAssetDetails - deve retornar erro se API vier vazio`() = runBlocking {
        coEvery { coinApi.getAssetDetails(assetId = "BTC") } returns emptyList()

        val result = repository.getAssetDetails(assetIdQuote = "BTC")

        assertTrue(result is Result.Error)
        val error = (result as Result.Error).error
        assertTrue(error is DataError.Local)
    }

    @Test
    fun `getAssetIcons - nao deve atualizar se ja existir no banco e nao for forceUpdate`() =
        runBlocking {
            coEvery { assetDao.getAllAssets() } returns listOf(TestUtils.sampleAssetEntity)
            repository.getAssetIcons()
            coVerify(exactly = 0) { coinApi.getAssetIcons() }
        }

    @Test
    fun `getAssetIcons - deve atualizar se lista local estiver vazia`() = runBlocking {
        coEvery { assetDao.getAllAssets() } returns emptyList()
        coEvery { assetDao.deleteAll() } just runs
        coEvery { assetDao.insertAll(any()) } just runs
        coEvery { coinApi.getAssetIcons() } returns listOf(TestUtils.sampleAssetIconDto)
        coEvery { exchangeRateDao.getAllExchangeRates() } returns emptyList()

        repository.getAssetIcons()

        coVerify { coinApi.getAssetIcons() }
        coVerify { assetDao.insertAll(any()) }
    }

    @Test
    fun `getExchangeRates - deve retornar erro NO_INTERNET quando ocorre UnknownHostException`() =
        runBlocking {
            coEvery { exchangeRateDao.getAllExchangeRates() } returns emptyList()
            coEvery { coinApi.getExchangeRates(any()) } throws java.net.UnknownHostException()

            val result = repository.getExchangeRates(assetIdBase = "BTC")

            assertTrue(result is Result.Error)
            val error = (result as Result.Error).error
            assertEquals(DataError.Remote.UNKNOWN, error)
        }

    @Test
    fun `syncExchangeRateIcons - deve atualizar URLs corretamente`() = runBlocking {
        val exchangeRates = listOf(
            TestUtils.sampleExchangeRateEntity.copy(assetIdQuote = "BTC"),
            TestUtils.sampleExchangeRateEntity.copy(assetIdQuote = "ETH")
        )
        val assets = listOf(
            TestUtils.sampleAssetEntity.copy(assetId = "BTC", imageUrl = "https://image.btc"),
            TestUtils.sampleAssetEntity.copy(assetId = "ETH", imageUrl = "https://image.eth")
        )

        coEvery { exchangeRateDao.getAllExchangeRates() } returns exchangeRates
        coEvery { assetDao.getAllAssets() } returns assets
        coEvery { exchangeRateDao.updateExchangeRateUrl(any(), any()) } just runs

        repository.syncExchangeRateIcons()

        coVerify { exchangeRateDao.updateExchangeRateUrl("BTC", "https://image.btc") }
        coVerify { exchangeRateDao.updateExchangeRateUrl("ETH", "https://image.eth") }
    }

}
