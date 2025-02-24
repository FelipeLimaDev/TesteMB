package com.fdlr.testemb.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class AssetEntity(
    @PrimaryKey val assetId: String,
    val imageUrl: String
)
