package com.fdlr.testemb.domain.model

data class Coin(
    val assetIdBase: String,
    val rates: List<Rate>
)