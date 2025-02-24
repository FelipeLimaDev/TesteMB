package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateDto(
    @SerializedName("asset_id_quote")
    val assetIdQuote: String?,
    val rate: Double?,
    val time: String?
)