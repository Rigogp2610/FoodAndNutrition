package com.robgar.foodandnutrition.framework

import com.robgar.foodandnutrition.framework.remote.ApiClient
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.framework.IngredientMapper.toDomainModel
import com.robgar.foodandnutrition.data.datasource.IIngredientsRemoteDataSource


class IngredientsRemoteDataSource : IIngredientsRemoteDataSource {

    override suspend fun searchIngredientsByName(name: String, number: Int, offset: Int): List<Ingredient> =
        ApiClient
            .instance
            .searchIngredientsByName(name, number, offset)
            .results
            .map { it.toDomainModel() }

    override suspend fun fetchInformationOfIngredient(id: Int, amount: Int): Ingredient =
        ApiClient
            .instance
            .fetchInformationOfIngredient(id, amount)
            .toDomainModel()
}