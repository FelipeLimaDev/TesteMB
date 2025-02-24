package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class AssetDto(
    @SerializedName("asset_id")
    val assetId: String?,
    val name: String? = null,
    @SerializedName("type_is_crypto")
    val isCrypto: Int?
)
