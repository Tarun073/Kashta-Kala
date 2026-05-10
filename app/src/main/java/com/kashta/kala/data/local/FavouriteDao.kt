package com.kashta.kala.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kashta.kala.data.model.FavouriteDesign

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: FavouriteDesign)

    @Query("SELECT * FROM favourites")
    fun getAllFavourites(): LiveData<List<FavouriteDesign>>

    @Query("SELECT * FROM favourites WHERE designId = :id")
    suspend fun getFavouriteById(id: Int): FavouriteDesign?

    @Delete
    suspend fun deleteFavourite(favourite: FavouriteDesign)

    @Query("SELECT EXISTS(SELECT 1 FROM favourites WHERE designId = :id)")
    suspend fun isFavourite(id: Int): Boolean
}