package com.robgar.foodandnutrition.framework

import com.robgar.foodandnutrition.framework.remote.ApiClient
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.framework.IngredientMapper.toDomainModel
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.framework.remote.ApiService
import javax.inject.Inject


class IngredientsRemoteDataSourceImp @Inject constructor(private val apiService: ApiService) :
    IngredientsRemoteDataSource {

    override suspend fun searchIngredientsByName(name: String, number: Int, offset: Int): List<Ingredient> =
        apiService
            .searchIngredientsByName(name, number, offset)
            .results
            .map { it.toDomainModel() }

    override suspend fun fetchInformationOfIngredient(id: Int, amount: Int): Ingredient =
        apiService
            .fetchInformationOfIngredient(id, amount)
            .toDomainModel()
}