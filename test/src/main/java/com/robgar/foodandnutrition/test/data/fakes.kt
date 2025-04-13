package com.robgar.foodandnutrition.test.data

import com.robgar.foodandnutrition.data.datasource.IngredientsLocalDataSource
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.data.repository.IngredientsRepositoryImp
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import com.robgar.foodandnutrition.test.sample10Ingredients
import com.robgar.foodandnutrition.test.sample30Ingredients
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

fun buildIngredientsRepositoryWith(
    localData: List<Ingredient> = emptyList(),
    remoteData: List<Ingredient> = emptyList()
): IngredientsRepository {
    val localDataSource = FakeLocalDataSource().apply { inMemoryValues.value = localData }
    val remoteDataSource = FakeRemoteDataSource().apply { ingredients = remoteData }

    return IngredientsRepositoryImp(localDataSource, remoteDataSource)
}

class FakeLocalDataSource : IngredientsLocalDataSource {

    val inMemoryValues = MutableStateFlow<List<Ingredient>>(emptyList())

    override fun findIngredientsByPrefix(
        prefix: String,
        limit: Int,
        offset: Int
    ): Flow<List<Ingredient>> {
        println("findIngredientsByPrefix: prefix -> $prefix | limit -> $limit | offset -> $offset")
        return inMemoryValues.map { ingredients ->
            ingredients.filter { ingredient ->  ingredient.queryTracking.startsWith(prefix, ignoreCase = true) }
                .drop(offset)
                .take(limit)
        }
    }

    override suspend fun saveIngredients(ingredients: List<Ingredient>, queryTracking: String) {
        inMemoryValues.value = ingredients
    }
}

class FakeRemoteDataSource : IngredientsRemoteDataSource {

    var ingredients = sample10Ingredients()

    override suspend fun searchIngredientsByName(
        name: String,
        number: Int,
        offset: Int
    ): List<Ingredient> {
        return ingredients
    }

    override suspend fun fetchInformationOfIngredient(id: Int, amount: Int): Ingredient {
        return ingredients.first { it.id == id }
    }
}