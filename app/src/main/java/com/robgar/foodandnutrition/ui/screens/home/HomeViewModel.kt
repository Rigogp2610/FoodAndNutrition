package com.robgar.foodandnutrition.ui.screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.data.Ingredient
import com.robgar.foodandnutrition.data.IngredientsRepository
import com.robgar.foodandnutrition.data.remote.ingredient.request.IngredientRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state get() = _state.asStateFlow()

    private val searchQuery = MutableStateFlow(IngredientRequest("", 0))

    private val repository = IngredientsRepository()

    private var ingredientRequest = IngredientRequest("", 0)


    init {
        viewModelScope.launch {
            searchQuery
                .debounce(200)
                .flatMapLatest { ingredientRequest ->
                    if (ingredientRequest.name.isEmpty()) {
                        flow { emit(emptyList<Ingredient>()) }
                    } else {
                        flow {
                            try {
                                val ingredients = repository.searchIngredientsByName(name = ingredientRequest.name, offset = ingredientRequest.offset)
                                emit(ingredients)
                            } catch (e: Exception) {
                                Log.e("HomeVM", "search error: ${e.message}")
                                emit(emptyList<Ingredient>())
                            }
                        }
                    }
                }
                .collect { ingredients ->
                    _state.update { it.copy(loading = false, ingredients = it.ingredients + ingredients) }
                }
        }
    }

    fun searchIngredientsByName(ingredientName: String) {
        viewModelScope.launch {
            ingredientRequest = IngredientRequest(ingredientName, 0)
            _state.value = UiState(true, ingredientFilter = ingredientName)
            searchQuery.value = ingredientRequest
        }
    }

    fun searchMoreIngredients() {
        viewModelScope.launch {
            ingredientRequest = ingredientRequest.copy(offset = ingredientRequest.offset + 10)
            searchQuery.value = ingredientRequest
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val ingredients: List<Ingredient> = emptyList(),
        val ingredientFilter: String = ""
    )
}