package com.kashta.kala.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kashta.kala.data.model.PortfolioItem

@Dao
interface PortfolioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: PortfolioItem)

    @Query("SELECT * FROM portfolio ORDER BY id DESC")
    fun getAllItems(): LiveData<List<PortfolioItem>>

    @Delete
    suspend fun deleteItem(item: PortfolioItem)

    @Query("DELETE FROM portfolio")
    suspend fun deleteAllItems()
}