package com.fdlr.testemb.coin.domain.usecase

import com.fdlr.testemb.domain.repository.CoinRepository

class GetAssetIconsUseCase(private val repository: CoinRepository) {
    suspend operator fun invoke() = repository.getAssetIcons()
}