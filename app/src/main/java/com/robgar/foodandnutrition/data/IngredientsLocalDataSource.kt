package com.robgar.foodandnutrition.data

import android.util.Log
import com.robgar.foodandnutrition.data.IngredientMapper.toDatabase
import com.robgar.foodandnutrition.data.IngredientMapper.toModel
import com.robgar.foodandnutrition.data.datasource.database.IngredientsDao
import com.robgar.foodandnutrition.data.datasource.database.ingredient.IngredientDB
import com.robgar.foodandnutrition.domain.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IngredientsLocalDataSource(private val ingredientsDao: IngredientsDao) {

    fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int) : Flow<List<Ingredient>> =
        ingredientsDao.findIngredientsByPrefix(prefix, limit, offset)
            .map { ingredientsDB -> ingredientsDB.map { ingredientDB -> ingredientDB.toModel() } }

    suspend fun saveIngredients(ingredients: List<Ingredient>, queryTracking: String) {
        val ingredientsToAdd = getIngredientsToAdd(ingredients, queryTracking)
        ingredientsDao.saveIngredients(ingredientsToAdd)
    }

    private suspend fun getIngredientsToAdd(ingredients: List<Ingredient>, queryTracking: String) : List<IngredientDB> {
        val ingredientsToAdd : MutableList<IngredientDB> = mutableListOf()
        ingredients.forEach { ingredient ->
            val ingredientDB = ingredientsDao.findIngredientById(ingredient.ingredientId)
            if (ingredientDB == null) {
                ingredientsToAdd.add(ingredient.toDatabase(queryTracking))
            } else if(queryTracking > ingredientDB.queryTracking) {
                ingredientsToAdd.add(ingredientDB.copy(queryTracking = queryTracking))
            }
        }
        Log.d("IngredientsLocalDataSource", "saveIngredients: queryTracking -> $queryTracking")
        Log.d("IngredientsLocalDataSource", "saveIngredients: ingredients -> $ingredients")
        Log.d("IngredientsLocalDataSource", "saveIngredients: ingredientsAdded -> $ingredientsToAdd")

        return ingredientsToAdd
    }
}