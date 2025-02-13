package com.robgar.foodandnutrition.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robgar.foodandnutrition.framework.database.ingredient.IngredientDB

@Database(entities = [IngredientDB::class], version = 1, exportSchema = false)
abstract class FoodAndNutritionDatabase : RoomDatabase()  {
    abstract fun ingredientsDao(): IngredientsDao
}