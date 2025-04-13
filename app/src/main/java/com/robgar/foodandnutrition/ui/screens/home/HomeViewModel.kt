package com.robgar.foodandnutrition.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.usecases.SearchIngredientsByNameUseCase
import com.robgar.foodandnutrition.framework.remote.ingredient.request.IngredientSearch
import com.robgar.foodandnutrition.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val searchIngredientsByNameUseCase: SearchIngredientsByNameUseCase) : ViewModel() {

    private var ingredientSearch = IngredientSearch("", 10, 0)
    private val ingredients : MutableList<Ingredient> = mutableListOf()

    private val _searchQuery = MutableStateFlow(ingredientSearch)
    val searchQuery = _searchQuery.asStateFlow()


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    val state: StateFlow<Result<List<Ingredient>>> =
        _searchQuery
            .debounce(200)
            .flatMapLatest { ingredientSearch ->
                Timber.tag("HomeVM").d("flatmaplatest: $ingredientSearch")
                if (ingredientSearch.name.isNotEmpty()) {
                    searchIngredientsByNameUseCase(
                        name = ingredientSearch.name, number = ingredientSearch.number, offset = ingredientSearch.offset)
                } else {
                    flow { emit(emptyList<Ingredient>()) }
                }
            }
            .flatMapConcat {
                val updatedIngredients = ingredients.toMutableList().apply {
                    addAll(it)
                }
                ingredients.clear()
                ingredients.addAll(updatedIngredients)
                Timber.tag("HomeVM").d( "currentIngredients: $ingredients \n newIngredient: $it")
                flow { emit(updatedIngredients) }
            }
            .stateAsResultIn(viewModelScope)


    fun searchIngredientsByName(ingredientName: String) {
        Timber.tag("HomeVM").d( "searchIngredientsByName: ingredient -> $ingredientName")
        val ingredientNameLower = ingredientName.lowercase()
        ingredients.clear()
        ingredientSearch = ingredientSearch.copy(offset = 0, name = ingredientNameLower, number = 10)
        _searchQuery.value = ingredientSearch

    }

    fun searchMoreIngredients(lastVisibleIndex: Int) {
        viewModelScope.launch {
            val ingredientsSize = ingredientSearch.number + ingredientSearch.offset
            Timber.tag("HomeVM").d( "next page: lastVisibleIndex -> $lastVisibleIndex | ingredients size -> ${ingredientsSize}")
            Timber.tag("HomeVM").d( "next page: number -> ${ingredientSearch.number} | offset -> ${ingredientSearch.offset}")
            if (lastVisibleIndex == ingredientsSize || lastVisibleIndex == (ingredientsSize - 1)) {
                ingredientSearch = ingredientSearch.copy(offset = ingredientSearch.offset + 10)
                _searchQuery.value = ingredientSearch
            }
        }
    }
}