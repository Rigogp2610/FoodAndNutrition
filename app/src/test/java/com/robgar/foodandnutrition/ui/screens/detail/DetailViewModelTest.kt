package com.robgar.foodandnutrition.ui.screens.detail

import app.cash.turbine.test
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.usecases.FetchInformationOfIngredientUseCase
import com.robgar.foodandnutrition.test.rules.CoroutinesTestRule
import com.robgar.foodandnutrition.test.sampleIngredient
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var fetchInformationOfIngredientUseCase: FetchInformationOfIngredientUseCase

    private lateinit var vm: DetailViewModel

    private val ingredient = sampleIngredient(1, 123, "banana")

    @Before
    fun setUp() {
        whenever(fetchInformationOfIngredientUseCase(ingredient.id)).thenReturn(flowOf(ingredient))
        vm = DetailViewModel(fetchInformationOfIngredientUseCase, 1)
    }

    @Test
    fun `UI is updated with the ingredient on start`() = runTest {
        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(ingredient), awaitItem())
        }
    }
}