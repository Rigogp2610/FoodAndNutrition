package com.robgar.foodandnutrition.data.repository

import com.robgar.foodandnutrition.data.datasource.IIngredientsLocalDataSource
import com.robgar.foodandnutrition.data.datasource.IIngredientsRemoteDataSource
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IIngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class IngredientsRepository(
    private val localDataSource: IIngredientsLocalDataSource,
    private val remoteDataSource: IIngredientsRemoteDataSource
) : IIngredientsRepository {

    override fun searchIngredientsByName(
        name: String,
        number: Int,
        offset: Int
    ): Flow<List<Ingredient>> =
        localDataSource.findIngredientsByPrefix(name, number, offset).onEach { ingredientsDB ->
            println("findIngredientsByPrefix: name -> $name | number -> $number | offset -> $offset")
            println("findIngredientsByPrefix: ingredientsDB -> $ingredientsDB")
            if (ingredientsDB.isEmpty()) {
                val remoteIngredients =
                    remoteDataSource.searchIngredientsByName(name, number, offset)
                localDataSource.saveIngredients(remoteIngredients, name)
            }
        }

    override fun fetchInformationOfIngredient(id: Int, amount: Int): Flow<Ingredient> = flow {
        emit(remoteDataSource.fetchInformationOfIngredient(id, amount))
    }

}