package com.robgar.foodandnutrition.data.datasource

import com.robgar.foodandnutrition.data.ApiClient
import com.robgar.foodandnutrition.data.Ingredient
import com.robgar.foodandnutrition.data.IngredientMapper.toDomainModel
import com.robgar.foodandnutrition.data.datasource.remote.ingredient.RemoteIngredient

class IngredientsRemoteDataSource {

    suspend fun searchIngredientsByName(name: String, number: Int = 10, offset: Int = 0): List<Ingredient> =
        ApiClient
            .instance
            .searchIngredientsByName(name, number, offset)
            .results
            .map { it.toDomainModel() }

    suspend fun fetchInformationOfIngredient(id: Int, amount: Int = 1): Ingredient =
        ApiClient
            .instance
            .fetchInformationOfIngredient(id, amount)
            .toDomainModel()
}