package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.domain.model.AssetDetails
import com.fdlr.testemb.domain.repository.CoinRepository
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result

class GetAssetDetailsUseCase(private val coinRepository: CoinRepository) {

    suspend fun getRemoteExchangeRateByAssetId(assetIdQuote: String): Result<AssetDetails, DataError> {
        return coinRepository.getAssetDetails(assetIdQuote)
    }

    suspend fun getLocalExchangeRateByAssetId(assetIdQuote: String) =
        coinRepository.getLocalExchangeRateByAssetId(assetIdQuote)
}