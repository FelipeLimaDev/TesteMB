package com.fdlr.testemb.data.source.remote.api

import com.fdlr.testemb.data.dto.AssetDetailsDto
import com.fdlr.testemb.data.dto.AssetDto
import com.fdlr.testemb.data.dto.AssetIconDto
import com.fdlr.testemb.data.dto.CoinDto
import com.fdlr.testemb.data.dto.ExchangeRateDto
import com.fdlr.testemb.data.utils.getCurrentIsoDateTime
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinApi {

    @GET("v1/exchangerate/{assetIdBase}")
    suspend fun getExchangeRates(
        @Path("assetIdBase") assetIdBase: String,
        @Query("invert") invert: Boolean = true,
        @Query("time") time: String = getCurrentIsoDateTime()
    ): CoinDto

    @GET("v1/exchangerate/{assetIdBase}/{assetIdQuote}")
    suspend fun getSpecificExchangeRate(
        @Path("assetIdBase") assetIdBase: String,
        @Path("assetIdQuote") assetIdQuote: String
    ): ExchangeRateDto

    @GET("v1/assets")
    suspend fun getAssets(): List<AssetDto>

    @GET("v1/assets/icons/512")
    suspend fun getAssetIcons(): List<AssetIconDto>

    @GET("v1/assets/{assetId}")
    suspend fun getAssetDetails(@Path("assetId") assetId: String): List<AssetDetailsDto>
}

