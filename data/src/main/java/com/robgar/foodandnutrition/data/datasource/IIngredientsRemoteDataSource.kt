package com.robgar.foodandnutrition.data.datasource

import com.robgar.foodandnutrition.domain.model.Ingredient

interface IIngredientsRemoteDataSource {
    suspend fun searchIngredientsByName(name: String, number: Int, offset: Int): List<Ingredient>
    suspend fun fetchInformationOfIngredient(id: Int, amount: Int): Ingredient
}
