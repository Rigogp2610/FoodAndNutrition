package com.robgar.foodandnutrition.ui.screens.home

import app.cash.turbine.test
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.usecases.SearchIngredientsByNameUseCase
import com.robgar.foodandnutrition.test.data.buildIngredientsRepositoryWith
import com.robgar.foodandnutrition.test.rules.CoroutinesTestRule
import com.robgar.foodandnutrition.test.sample10Ingredients
import com.robgar.foodandnutrition.test.sample30Ingredients
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local data source is empty`() = runTest {
        val remoteData = sample10Ingredients()
        val vm = buildViewModelWith(remoteData = remoteData)

        vm.searchIngredientsByName("b")

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(emptyList<Ingredient>()), awaitItem())
            assertEquals(Result.Success(remoteData), awaitItem())
        }
    }

    @Test
    fun `Data is loaded from local data source when available`() = runTest {
        val localData = sample10Ingredients()
        val vm = buildViewModelWith(localData = localData)

        vm.searchIngredientsByName("b")

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(localData), awaitItem())
        }
    }

    @Test
    fun `Get ingredients successfully that start with ba`() = runTest {
        val localData = sample30Ingredients()
        val vm = buildViewModelWith(localData = localData)

        vm.searchIngredientsByName("ba")

        vm.state.test {
            awaitItem()
            val result = awaitItem()
            assertEquals(3, (result as Result.Success).data.size)
        }
    }

    @Test
    fun `Get zero ingredients that start with bat`() = runTest {
        val localData = sample30Ingredients()
        val vm = buildViewModelWith(localData = localData)

        vm.searchIngredientsByName("bat")

        vm.state.test {
            awaitItem()
            val result = awaitItem()
            assertEquals(0, (result as Result.Success).data.size)
        }
    }

    @Test
    fun `Get eight ingredients that start with c`() = runTest {
        val localData = sample30Ingredients()
        val vm = buildViewModelWith(localData = localData)

        vm.searchIngredientsByName("c")

        vm.state.test {
            awaitItem()
            val result = awaitItem()
            assertEquals(8, (result as Result.Success).data.size)
        }
    }

    @Test
    fun `Get ingredients of second page that start with b`() = runTest {
        val localData = sample30Ingredients()
        val vm = buildViewModelWith(localData = localData)

        vm.state.test {
            vm.searchIngredientsByName("b")
            awaitItem()
            awaitItem()
            vm.searchMoreIngredients(10)
            val result = awaitItem()
            assertEquals(13, (result as Result.Success).data.size)
        }
    }

    private fun buildViewModelWith(
        localData: List<Ingredient> = emptyList(),
        remoteData: List<Ingredient> = emptyList()
    ): HomeViewModel {
        return HomeViewModel(
            SearchIngredientsByNameUseCase(
                buildIngredientsRepositoryWith(localData, remoteData)
            )
        )
    }
}