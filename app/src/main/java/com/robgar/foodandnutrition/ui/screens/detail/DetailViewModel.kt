package com.robgar.foodandnutrition.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.usecases.FetchInformationOfIngredientUseCase
import com.robgar.foodandnutrition.stateAsResultIn
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel(fetchInformationOfIngredientUseCase: FetchInformationOfIngredientUseCase, id: Int) : ViewModel() {

    val state: StateFlow<Result<Ingredient>> = fetchInformationOfIngredientUseCase(id)
        .stateAsResultIn(viewModelScope)
}