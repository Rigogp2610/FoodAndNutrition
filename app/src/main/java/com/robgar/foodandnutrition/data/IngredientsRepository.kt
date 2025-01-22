package com.robgar.foodandnutrition.data

import android.util.Log
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.domain.Ingredient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class IngredientsRepository(
    private val localDataSource: IngredientsLocalDataSource,
    private val remoteDataSource: IngredientsRemoteDataSource
) {

    fun searchIngredientsByName(
        name: String,
        number: Int = 10,
        offset: Int = 0
    ): Flow<List<Ingredient>> =
        localDataSource.findIngredientsByPrefix(name, number, offset).onEach { ingredientsDB ->
            Log.d(
                "IngredientsRepository",
                "findIngredientsByPrefix: name -> $name | number -> $number | offset -> $offset"
            )
            Log.d(
                "IngredientsRepository",
                "findIngredientsByPrefix: ingredientsDB -> $ingredientsDB"
            )
            if (ingredientsDB.isEmpty()) {
                val remoteIngredients =
                    remoteDataSource.searchIngredientsByName(name, number, offset)
                localDataSource.saveIngredients(remoteIngredients, name)
            }
        }

    fun fetchInformationOfIngredient(id: Int, amount: Int = 1): Flow<Ingredient> = flow {
        emit(remoteDataSource.fetchInformationOfIngredient(id, amount))
    }

}