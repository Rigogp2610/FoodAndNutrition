package com.robgar.foodandnutrition.framework.database.ingredient

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "Ingredient",
    indices = [Index(value = ["name"], unique = false), Index(value = ["queryTracking"], unique = false), Index(value = ["ingredientId"], unique = true)]
)
data class IngredientDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val ingredientId: Int,
    val name: String,
    val image: String,
    val queryTracking: String = ""
)