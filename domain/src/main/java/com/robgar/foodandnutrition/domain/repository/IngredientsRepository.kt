package com.robgar.foodandnutrition.domain.repository

import com.robgar.foodandnutrition.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientsRepository {

    fun searchIngredientsByName(
        name: String,
        number: Int,
        offset: Int
    ): Flow<List<Ingredient>>

    fun fetchInformationOfIngredient(id: Int, amount: Int = 1): Flow<Ingredient>
}