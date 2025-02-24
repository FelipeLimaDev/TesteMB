package com.fdlr.testemb.domain.model

data class ExchangeRate(
    val time: String,
    val assetIdBase: String,
    val assetIdQuote: String,
    val rate: Double,
    val imageUrl: String
)
