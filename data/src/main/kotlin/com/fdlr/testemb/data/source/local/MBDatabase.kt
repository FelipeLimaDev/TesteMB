package com.fdlr.testemb.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fdlr.testemb.data.source.local.dao.AssetDao
import com.fdlr.testemb.data.source.local.dao.ExchangeRateDao
import com.fdlr.testemb.data.source.local.model.AssetEntity
import com.fdlr.testemb.data.source.local.model.ExchangeRateEntity

@Database(entities = [ExchangeRateEntity::class, AssetEntity::class], version = 1, exportSchema = false)
abstract class MBDatabase : RoomDatabase() {
    abstract fun exchangeRateDao(): ExchangeRateDao
    abstract fun assetDao(): AssetDao

    companion object {
        @Volatile
        private var INSTANCE: MBDatabase? = null

        fun getDatabase(context: Context): MBDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MBDatabase::class.java,
                    "mb_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
