package com.robgar.foodandnutrition.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.data.Ingredient
import com.robgar.foodandnutrition.data.IngredientsRepository
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {

    private val repository = IngredientsRepository(IngredientsRemoteDataSource())

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()


    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, ingredient = repository.fetchInformationOfIngredient(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val ingredient: Ingredient? = null
    )
}