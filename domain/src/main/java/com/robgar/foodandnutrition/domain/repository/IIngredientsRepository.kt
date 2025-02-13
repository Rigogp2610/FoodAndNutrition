package com.robgar.foodandnutrition.domain.repository

import com.robgar.foodandnutrition.domain.model.Ingredient
import kotlinx.coroutines.flow.Flow

interface IIngredientsRepository {

    fun searchIngredientsByName(
        name: String,
        number: Int,
        offset: Int
    ): Flow<List<Ingredient>>

    fun fetchInformationOfIngredient(id: Int, amount: Int): Flow<Ingredient>
}