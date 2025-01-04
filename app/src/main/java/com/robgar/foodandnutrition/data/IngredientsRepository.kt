package com.robgar.foodandnutrition.data

import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource

class IngredientsRepository(
    private val ingredientsRemoteDataSource: IngredientsRemoteDataSource
) {

    suspend fun searchIngredientsByName(name: String, number: Int = 10, offset: Int = 0): List<Ingredient> =
        ingredientsRemoteDataSource.searchIngredientsByName(name, number, offset)

    suspend fun fetchInformationOfIngredient(id: Int, amount: Int = 1): Ingredient =
        ingredientsRemoteDataSource.fetchInformationOfIngredient(id, amount)

}