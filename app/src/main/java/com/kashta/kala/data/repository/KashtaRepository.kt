package com.kashta.kala.data.repository

import androidx.lifecycle.LiveData
import com.kashta.kala.data.local.KashtaDatabase
import com.kashta.kala.data.model.FavouriteDesign
import com.kashta.kala.data.model.PortfolioItem
import com.kashta.kala.data.model.Quote

class KashtaRepository(database: KashtaDatabase) {

    private val quoteDao = database.quoteDao()
    private val portfolioDao = database.portfolioDao()
    private val favouriteDao = database.favouriteDao()

    // --- Quotes ---
    val allQuotes: LiveData<List<Quote>> = quoteDao.getAllQuotes()

    suspend fun insertQuote(quote: Quote) = quoteDao.insertQuote(quote)

    suspend fun deleteQuote(quote: Quote) = quoteDao.deleteQuote(quote)

    suspend fun getQuoteById(id: Int) = quoteDao.getQuoteById(id)

    // --- Portfolio ---
    val allPortfolioItems: LiveData<List<PortfolioItem>> = portfolioDao.getAllItems()

    suspend fun insertPortfolioItem(item: PortfolioItem) = portfolioDao.insertItem(item)

    suspend fun deletePortfolioItem(item: PortfolioItem) = portfolioDao.deleteItem(item)

    // --- Favourites ---
    val allFavourites: LiveData<List<FavouriteDesign>> = favouriteDao.getAllFavourites()

    suspend fun insertFavourite(favourite: FavouriteDesign) =
        favouriteDao.insertFavourite(favourite)

    suspend fun deleteFavourite(favourite: FavouriteDesign) =
        favouriteDao.deleteFavourite(favourite)

    suspend fun isFavourite(id: Int) = favouriteDao.isFavourite(id)

    suspend fun getFavouriteById(id: Int) = favouriteDao.getFavouriteById(id)
}