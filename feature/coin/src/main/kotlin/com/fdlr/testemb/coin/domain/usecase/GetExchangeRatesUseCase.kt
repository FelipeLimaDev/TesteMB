package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.domain.model.Coin
import com.fdlr.testemb.domain.repository.CoinRepository
import com.fdlr.testemb.domain.utils.DataError
import com.fdlr.testemb.domain.utils.Result

class GetExchangeRatesUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke(
        assetIdBase: String = "BRL" // Default value
    ): Result<Coin, DataError> {
        return repository.getExchangeRates(assetIdBase)
    }
}
