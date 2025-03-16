package com.robgar.foodandnutrition.data.datasource

import com.robgar.foodandnutrition.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IIngredientsLocalDataSource {
    fun findIngredientsByPrefix(prefix: String, limit: Int, offset: Int) : Flow<List<Ingredient>>
    suspend fun saveIngredients(ingredients: List<Ingredient>, queryTracking: String)

}