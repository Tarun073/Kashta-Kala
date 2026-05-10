package com.kashta.kala.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kashta.kala.data.model.FavouriteDesign
import com.kashta.kala.data.model.PortfolioItem
import com.kashta.kala.data.model.Quote

@Database(
    entities = [Quote::class, PortfolioItem::class, FavouriteDesign::class],
    version = 1,
    exportSchema = false
)
abstract class KashtaDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
    abstract fun portfolioDao(): PortfolioDao
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        @Volatile
        private var INSTANCE: KashtaDatabase? = null

        fun getDatabase(context: Context): KashtaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KashtaDatabase::class.java,
                    "kashta_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}