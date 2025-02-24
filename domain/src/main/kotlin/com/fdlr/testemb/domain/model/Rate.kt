package com.fdlr.testemb.domain.model

data class Rate(
    val id : Int = 0,
    val assetIdQuote: String,
    val rate: Double,
    val time: String,
    val imageUrl: String? = null
)