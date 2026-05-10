package com.kashta.kala.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val itemName: String,
    val woodType: String,
    val length: Double,
    val width: Double,
    val height: Double,
    val areasqft: Double,
    val volumeCuft: Double,
    val materialCost: Double,
    val laborCost: Double,
    val extraCost: Double,
    val totalCost: Double,
    val date: String
)