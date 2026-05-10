package com.kashta.kala.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouriteDesign(
    @PrimaryKey
    val designId: Int,
    val designName: String,
    val category: String,
    val imageRes: Int
)