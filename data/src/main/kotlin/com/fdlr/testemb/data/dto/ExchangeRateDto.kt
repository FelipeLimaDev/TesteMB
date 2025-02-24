package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateDto(
    val time: String?,
    @SerializedName("asset_id_base")
    val assetIdBase: String?,
    @SerializedName("asset_id_quote")
    val assetIdQuote: String?,
    val rate: Double?
)
