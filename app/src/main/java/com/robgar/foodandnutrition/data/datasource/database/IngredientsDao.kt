package com.robgar.foodandnutrition.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robgar.foodandnutrition.data.datasource.database.ingredient.IngredientDB
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM Ingredient WHERE name LIKE :prefix || '%' " +
            "AND (:prefix IN (SELECT DISTINCT queryTracking FROM Ingredient) " +
            "OR queryTracking LIKE :prefix || '%') " +
            "LIMIT :limit OFFSET :offset")
    fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int): Flow<List<IngredientDB>>

    //@Query("SELECT * FROM Ingredient WHERE name LIKE :prefix || '%' LIMIT :limit OFFSET :offset")
    //fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int): Flow<List<IngredientDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIngredients(ingredients: List<IngredientDB>)
}