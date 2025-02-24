package com.fdlr.testemb.domain.repository

import com.fdlr.testemb.domain.model.AssetDetails
import com.fdlr.testemb.domain.model.Asset
import com.fdlr.testemb.domain.model.Coin
import com.fdlr.testemb.domain.model.ExchangeRate
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result

interface CoinRepository {
    suspend fun getExchangeRates(assetIdBase: String): Result<Coin, DataError>

    suspend fun getSpecificExchangeRate(
        assetIdBase: String,
        assetIdQuote: String
    ): Result<ExchangeRate, DataError>

    suspend fun getLocalExchangeRateByAssetId(assetIdQuote: String): Result<ExchangeRate, DataError>

    suspend fun getAssets(): Result<List<Asset>, DataError>

    suspend fun getAssetDetails(assetIdQuote: String): Result<AssetDetails, DataError>

    suspend fun getAssetIcons()
}