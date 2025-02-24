package com.fdlr.testemb.coin.di

import com.fdlr.testemb.coin.domain.usecase.GetAssetDetailsUseCase
import com.fdlr.testemb.coin.domain.usecase.GetAssetIconsUseCase
import com.fdlr.testemb.coin.domain.usecase.GetExchangeRatesUseCase
import com.fdlr.testemb.coin.presentation.ui.details.CoinDetailsViewModel
import com.fdlr.testemb.coin.presentation.ui.home.CoinHomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

private val useCaseModule = module {
    factoryOf(::GetExchangeRatesUseCase)
    factoryOf(::GetAssetIconsUseCase)
    factoryOf(::GetAssetDetailsUseCase)
}

private val viewModelModule = module {
    viewModelOf(::CoinHomeViewModel)
    viewModelOf(::CoinDetailsViewModel)
}

val coinModule = listOf(useCaseModule, viewModelModule)