package com.kashta.kala.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kashta.kala.data.model.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getAllQuotes(): LiveData<List<Quote>>

    @Query("SELECT * FROM quotes WHERE id = :id")
    suspend fun getQuoteById(id: Int): Quote?

    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Query("DELETE FROM quotes")
    suspend fun deleteAllQuotes()
}