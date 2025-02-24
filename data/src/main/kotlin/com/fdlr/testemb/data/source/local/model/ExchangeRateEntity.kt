package com.fdlr.testemb.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRateEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val assetIdQuote: String,
    val rate: Double,
    val timestamp: String,
    val assetIdBase: String,
    val url: String = String()
)
