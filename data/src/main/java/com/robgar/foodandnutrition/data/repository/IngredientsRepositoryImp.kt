package com.robgar.foodandnutrition.data.repository

import com.robgar.foodandnutrition.data.datasource.IngredientsLocalDataSource
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class IngredientsRepositoryImp @Inject constructor(
    private val localDataSource: IngredientsLocalDataSource,
    private val remoteDataSource: IngredientsRemoteDataSource
) : IngredientsRepository {

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