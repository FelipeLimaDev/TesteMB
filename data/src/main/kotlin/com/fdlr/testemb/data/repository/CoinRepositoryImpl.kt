package com.fdlr.testemb.data.repository

import com.fdlr.testemb.data.exceptions.DataErrorNotFoundException
import com.fdlr.testemb.data.mappers.AssetDetailsMapper
import com.fdlr.testemb.data.mappers.AssetIconMapper
import com.fdlr.testemb.data.mappers.AssetMapper
import com.fdlr.testemb.data.mappers.CoinMapper
import com.fdlr.testemb.data.mappers.ExchangeRateMapper
import com.fdlr.testemb.data.source.local.dao.AssetDao
import com.fdlr.testemb.data.source.local.dao.ExchangeRateDao
import com.fdlr.testemb.data.source.remote.api.CoinApi
import com.fdlr.testemb.data.utils.safeCall
import com.fdlr.testemb.domain.model.AssetDetails
import com.fdlr.testemb.domain.model.Asset
import com.fdlr.testemb.domain.model.Coin
import com.fdlr.testemb.domain.model.ExchangeRate
import com.fdlr.testemb.domain.repository.CoinRepository
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result

class CoinRepositoryImpl(
    private val coinApi: CoinApi,
    private val assetDao: AssetDao,
    private val exchangeRateDao: ExchangeRateDao
) : CoinRepository {

    private val coinMapper = CoinMapper()
    private val assetIconMapper = AssetIconMapper()
    private val assetMapper = AssetMapper()
    private val exchangeRateMapper = ExchangeRateMapper()
    private val assetDetailsMapper = AssetDetailsMapper()

    override suspend fun getExchangeRates(assetIdBase: String): Result<Coin, DataError> {
        return safeCall {
            var exchangeRates = exchangeRateDao.getAllExchangeRates()

            if (exchangeRates.isNotEmpty()) {
                syncExchangeRateIcons()
                return@safeCall coinMapper.toDomain(exchangeRates)
            }

            val apiExchangeRates = coinApi.getExchangeRates(assetIdBase)
            exchangeRateDao.deleteAll()
            exchangeRateDao.insertAll(coinMapper.toEntity(apiExchangeRates))

            updateAssetIcons(forceUpdate = true)

            exchangeRates = exchangeRateDao.getAllExchangeRates()
            coinMapper.toDomain(exchangeRates)
        }
    }

    override suspend fun getLocalExchangeRateByAssetId(assetIdQuote: String): Result<ExchangeRate, DataError> {
        return safeCall {
            exchangeRateDao.getExchangeRateByAssetId(assetIdQuote)?.let {
                exchangeRateMapper.toDomain(it)
            } ?: throw DataErrorNotFoundException()
        }
    }

    override suspend fun getSpecificExchangeRate(
        assetIdBase: String,
        assetIdQuote: String
    ): Result<ExchangeRate, DataError> {
        return safeCall {
            val exchangeRate = coinApi.getSpecificExchangeRate(assetIdBase, assetIdQuote)
            exchangeRateMapper.fromDto(exchangeRate)
        }
    }

    override suspend fun getAssets(): Result<List<Asset>, DataError> {
        return safeCall {
            coinApi.getAssets().map { assetMapper.toDomain(it) }
        }
    }

    override suspend fun getAssetDetails(assetIdQuote: String): Result<AssetDetails, DataError> {
        return safeCall {
            val assetDetails = coinApi.getAssetDetails(assetIdQuote).firstOrNull()
                ?: throw DataErrorNotFoundException()
            assetDetailsMapper.toDomain(assetDetails)
        }
    }

    override suspend fun getAssetIcons() {
        updateAssetIcons(forceUpdate = false)
    }

    private suspend fun updateAssetIcons(forceUpdate: Boolean) {
        safeCall {
            val localAssetIcons = assetDao.getAllAssets()

            if (!forceUpdate && localAssetIcons.isNotEmpty()) return@safeCall

            val remoteIcons = coinApi.getAssetIcons()
            val entityIcons = assetIconMapper.toEntity(remoteIcons)

            assetDao.deleteAll()
            assetDao.insertAll(entityIcons)

            syncExchangeRateIcons()
        }
    }

    suspend fun syncExchangeRateIcons() {
        val localRates = exchangeRateDao.getAllExchangeRates()
        if (localRates.isEmpty()) return

        val assetMap = assetDao.getAllAssets().associateBy { it.assetId.removePrefix("$") }

        localRates.forEach { rate ->
            assetMap[rate.assetIdQuote]?.let { asset ->
                exchangeRateDao.updateExchangeRateUrl(rate.assetIdQuote, asset.imageUrl)
            }
        }
    }
}
