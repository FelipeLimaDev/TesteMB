package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AssetDetailsDto(
    @SerializedName("asset_id") val assetId: String? = null,
    @SerializedName("chain_addresses") val chainAddresses: List<ChainAddressDto>? = null,
    @SerializedName("data_end") val dataEnd: String? = null,
    @SerializedName("data_orderbook_end") val dataOrderbookEnd: String? = null,
    @SerializedName("data_orderbook_start") val dataOrderbookStart: String? = null,
    @SerializedName("data_quote_end") val dataQuoteEnd: String? = null,
    @SerializedName("data_quote_start") val dataQuoteStart: String? = null,
    @SerializedName("data_start") val dataStart: String? = null,
    @SerializedName("data_symbols_count") val dataSymbolsCount: Int? = null,
    @SerializedName("data_trade_end") val dataTradeEnd: String? = null,
    @SerializedName("data_trade_start") val dataTradeStart: String? = null,
    @SerializedName("name") val assetName: String? = null,
    @SerializedName("price_usd") val priceUsd: Double? = null,
    @SerializedName("type_is_crypto") val typeIsCrypto: Int? = null,
    @SerializedName("volume_1day_usd") val volume1DayUsd: Double? = null,
    @SerializedName("volume_1hrs_usd") val volume1HrsUsd: Double? = null,
    @SerializedName("volume_1mth_usd") val volume1MthUsd: Double? = null
)
