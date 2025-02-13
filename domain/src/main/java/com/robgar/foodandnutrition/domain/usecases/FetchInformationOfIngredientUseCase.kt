package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.domain.repository.IIngredientsRepository

class FetchInformationOfIngredientUseCase(private val ingredientsRepository: IIngredientsRepository) {

    operator fun invoke(id: Int, amount: Int = 1) = ingredientsRepository.fetchInformationOfIngredient(id, amount)
}