package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AssetIconDto(
    @SerializedName("asset_id")
    val assetId: String?,
    val url: String?
)
