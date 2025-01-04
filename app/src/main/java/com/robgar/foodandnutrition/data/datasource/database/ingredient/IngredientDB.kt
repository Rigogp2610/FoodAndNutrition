package com.robgar.foodandnutrition.data.datasource.database.ingredient

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Ingredient",
    indices = [androidx.room.Index(value = ["name"], unique = false), androidx.room.Index(value = ["queryTracking"], unique = false)]
)
data class IngredientDB(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val queryTracking: String
)