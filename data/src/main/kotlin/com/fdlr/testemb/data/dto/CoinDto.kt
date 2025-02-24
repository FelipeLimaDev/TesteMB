package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    @SerializedName("asset_id_base")
    val assetIdBase: String?,
    val rates: List<RateDto?>?
)