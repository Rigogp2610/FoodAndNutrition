package com.robgar.foodandnutrition.framework

import android.util.Log
import com.robgar.foodandnutrition.framework.IngredientMapper.toDatabase
import com.robgar.foodandnutrition.framework.IngredientMapper.toDomain
import com.robgar.foodandnutrition.data.datasource.IngredientsLocalDataSource
import com.robgar.foodandnutrition.framework.database.IngredientsDao
import com.robgar.foodandnutrition.framework.database.ingredient.IngredientDB
import com.robgar.foodandnutrition.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IngredientsLocalDataSourceImp @Inject constructor(private val ingredientsDao: IngredientsDao) :
    IngredientsLocalDataSource {

    override fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int) : Flow<List<Ingredient>> =
        ingredientsDao.findIngredientsByPrefix(prefix, limit, offset)
            .map { ingredientsDB -> ingredientsDB.map { ingredientDB -> ingredientDB.toDomain() } }

    override suspend fun saveIngredients(ingredients: List<Ingredient>, queryTracking: String) {
        val ingredientsToAdd = getIngredientsToAdd(ingredients, queryTracking)
        ingredientsDao.saveIngredients(ingredientsToAdd)
    }

    private suspend fun getIngredientsToAdd(ingredients: List<Ingredient>, queryTracking: String) : List<IngredientDB> {
        val newIngredients : MutableList<IngredientDB> = mutableListOf()
        ingredients.forEach { ingredient ->
            val ingredientDB = ingredientsDao.findIngredientById(ingredient.ingredientId)
            if (ingredientDB == null) {
                newIngredients.add(ingredient.toDatabase(queryTracking))
            } else if(queryTracking > ingredientDB.queryTracking) {
                newIngredients.add(ingredientDB.copy(queryTracking = queryTracking))
            }
        }
        Log.d("IngredientsLocalDataSource", "saveIngredients: queryTracking -> $queryTracking")
        Log.d("IngredientsLocalDataSource", "saveIngredients: ingredients -> $ingredients")
        Log.d("IngredientsLocalDataSource", "saveIngredients: ingredientsAdded -> $newIngredients")

        return newIngredients
    }
}