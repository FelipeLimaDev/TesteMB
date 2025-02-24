package com.fdlr.testemb.data.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ChainAddressDto(
    @SerializedName("address") val address: String? = null,
    @SerializedName("chain_id") val chainId: String? = null,
    @SerializedName("network_id") val networkId: String? = null
)
