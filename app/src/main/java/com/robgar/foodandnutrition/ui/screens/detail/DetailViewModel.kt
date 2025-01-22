package com.robgar.foodandnutrition.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.Ingredient
import com.robgar.foodandnutrition.data.IngredientsRepository
import com.robgar.foodandnutrition.stateAsResultIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: IngredientsRepository, private val id: Int) : ViewModel() {

    val state: StateFlow<Result<Ingredient>> = repository.fetchInformationOfIngredient(id)
        .stateAsResultIn(viewModelScope)
}