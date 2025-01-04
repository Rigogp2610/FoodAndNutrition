package com.robgar.foodandnutrition.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QueryTracking (
    @PrimaryKey
    val prefix: String,
    val totalIngredientesFetched: Int
)