package com.robgar.foodandnutrition.ui.screens.detail

import app.cash.turbine.test
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.usecases.FetchInformationOfIngredientUseCase
import com.robgar.foodandnutrition.test.data.buildIngredientsRepositoryWith
import com.robgar.foodandnutrition.test.rules.CoroutinesTestRule
import com.robgar.foodandnutrition.test.sample30Ingredients
import com.robgar.foodandnutrition.test.sampleIngredient
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailIntegrationTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var vm: DetailViewModel

    @Before
    fun setUp() {
        val ingredientsRepository = buildIngredientsRepositoryWith(remoteData = sample30Ingredients())
        vm = DetailViewModel(FetchInformationOfIngredientUseCase(ingredientsRepository), 1)
    }

    @Test
    fun `Get ingredient detail successfully`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(sampleIngredient(1, 123, "banana", "ba")), awaitItem())
        }
    }
}