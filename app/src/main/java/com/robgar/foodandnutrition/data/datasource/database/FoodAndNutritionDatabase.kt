package com.robgar.foodandnutrition.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robgar.foodandnutrition.data.datasource.database.ingredient.IngredientDB

@Database(entities = [IngredientDB::class], version = 1, exportSchema = false)
abstract class FoodAndNutritionDatabase : RoomDatabase()  {
    abstract fun ingredientsDao(): IngredientsDao
}