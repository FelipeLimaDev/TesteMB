package com.fdlr.testemb.domain.model

data class AssetDetails(
    val assetId: String?,
    val chainAddresses: List<ChainAddress>?,
    val dataEnd: String?,
    val dataOrderbookEnd: String?,
    val dataOrderbookStart: String?,
    val dataQuoteEnd: String?,
    val dataQuoteStart: String?,
    val dataStart: String?,
    val dataSymbolsCount: Int?,
    val dataTradeEnd: String?,
    val dataTradeStart: String?,
    val assetName: String?,
    val priceUsd: Double?,
    val typeIsCrypto: Int?,
    val volume1DayUsd: Double?,
    val volume1HrsUsd: Double?,
    val volume1MthUsd: Double?
)