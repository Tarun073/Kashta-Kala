package com.kashta.kala.data.model

data class FurnitureDesign(
    val id: Int,
    val name: String,
    val category: String,
    val description: String,
    val imageRes: Int,
    val suggestedWood: String,
    val estimatedPrice: Int,
    var isFavourite: Boolean = false
)