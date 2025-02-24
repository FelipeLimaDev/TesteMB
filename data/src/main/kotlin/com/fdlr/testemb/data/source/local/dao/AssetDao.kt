package com.fdlr.testemb.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fdlr.testemb.data.source.local.model.AssetEntity

@Dao
interface AssetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(assets: List<AssetEntity>)

    @Query("SELECT * FROM assets")
    suspend fun getAllAssets(): List<AssetEntity>

    @Query("DELETE FROM assets")
    suspend fun deleteAll()
}