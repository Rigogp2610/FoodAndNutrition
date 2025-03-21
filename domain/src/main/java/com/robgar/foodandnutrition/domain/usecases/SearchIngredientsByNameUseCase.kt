package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchIngredientsByNameUseCase @Inject constructor(private val ingredientsRepository: IngredientsRepository) {

    operator fun invoke(name: String, number: Int = 10, offset: Int = 0): Flow<List<Ingredient>> =
        ingredientsRepository.searchIngredientsByName(name, number, offset)
}