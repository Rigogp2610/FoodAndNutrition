package com.robgar.foodandnutrition.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.data.Ingredient
import com.robgar.foodandnutrition.data.IngredientsRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {

    private val repository = IngredientsRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, ingredient = repository.fetchInformationOfIngredient(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val ingredient: Ingredient? = null
    )
}