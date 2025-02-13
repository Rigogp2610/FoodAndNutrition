package com.robgar.foodandnutrition.framework.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.robgar.foodandnutrition.framework.database.ingredient.IngredientDB
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientsDao {

    @Query("SELECT * FROM Ingredient WHERE queryTracking LIKE :prefix || '%' " +
            "LIMIT :limit OFFSET :offset")
    fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int): Flow<List<IngredientDB>>

    @Query("SELECT * FROM Ingredient WHERE ingredientId=:ingredientId")
    suspend fun findIngredientById(ingredientId: Int): IngredientDB?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveIngredients(ingredients: List<IngredientDB>)
}