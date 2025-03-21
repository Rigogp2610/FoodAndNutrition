package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import javax.inject.Inject

class FetchInformationOfIngredientUseCase @Inject constructor(private val ingredientsRepository: IngredientsRepository) {

    operator fun invoke(id: Int, amount: Int = 1) = ingredientsRepository.fetchInformationOfIngredient(id, amount)
}