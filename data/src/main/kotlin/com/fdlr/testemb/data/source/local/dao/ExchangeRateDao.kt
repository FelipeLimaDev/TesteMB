package com.fdlr.testemb.data.source.local.dao

import androidx.room.*
import com.fdlr.testemb.data.source.local.model.ExchangeRateEntity

@Dao
interface ExchangeRateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<ExchangeRateEntity>)

    @Query(""" UPDATE exchange_rates SET rate = :newRate, timestamp = :newTimestamp WHERE assetIdQuote = :assetIdQuote """)
    suspend fun updateRateAndTimestamp(
        assetIdQuote: String,
        newRate: Double,
        newTimestamp: String
    )

    @Query("SELECT * FROM exchange_rates")
    suspend fun getAllExchangeRates(): List<ExchangeRateEntity>

    @Query("UPDATE exchange_rates SET url = :imageUrl WHERE assetIdQuote = :assetIdQuote")
    suspend fun updateExchangeRateUrl(assetIdQuote: String, imageUrl: String)

    @Query("SELECT * FROM exchange_rates WHERE assetIdQuote = :assetIdQuote LIMIT 1")
    suspend fun getExchangeRateByAssetId(assetIdQuote: String): ExchangeRateEntity?

    @Query("DELETE FROM exchange_rates")
    suspend fun deleteAll()
}
