package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IIngredientsRepository
import kotlinx.coroutines.flow.Flow

class SearchIngredientsByNameUseCase(private val ingredientsRepository: IIngredientsRepository) {

    operator fun invoke(name: String, number: Int = 10, offset: Int = 0): Flow<List<Ingredient>> =
        ingredientsRepository.searchIngredientsByName(name, number, offset)
}