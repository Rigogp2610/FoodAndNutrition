package com.robgar.foodandnutrition.ui.screens.home

import app.cash.turbine.test
import com.robgar.foodandnutrition.Result
import com.robgar.foodandnutrition.domain.usecases.SearchIngredientsByNameUseCase
import com.robgar.foodandnutrition.test.rules.CoroutinesTestRule
import com.robgar.foodandnutrition.test.sample10Ingredients
import com.robgar.foodandnutrition.test.sample30Ingredients
import com.robgar.foodandnutrition.ui.screens.TestTree
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import timber.log.Timber

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var searchIngredientsByNameUseCase: SearchIngredientsByNameUseCase

    private lateinit var vm: HomeViewModel

    private lateinit var testTree: TestTree

    @Before
    fun setUp() {
        testTree = TestTree()
        Timber.uprootAll()
        Timber.plant(testTree)
        vm = HomeViewModel(searchIngredientsByNameUseCase)
    }

    @After
    fun tearDown() {
        Timber.uprootAll()
    }

    @Test
    fun `Search ingredients are not requested if not searched yet`() = runTest {
        vm.state.first()
        runCurrent()

        verify(searchIngredientsByNameUseCase, times(0)).invoke("b")
    }

    @Test
    fun `Search ingredients are requested if searched`() = runTest {
        val ingredients = sample10Ingredients()
        whenever(searchIngredientsByNameUseCase.invoke("b")).thenReturn(flowOf(ingredients))

        vm.searchIngredientsByName("b")

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(ingredients), awaitItem())
        }
    }

    @Test
    fun `Error ir propagated when request fails`() = runTest {
        val error = RuntimeException("Boom!")
        whenever(searchIngredientsByNameUseCase("-")).thenThrow(error)


        vm.searchIngredientsByName("-")

        vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            val exceptionMessage = (awaitItem() as Result.Error).throwable.message
            assertEquals("Boom!", exceptionMessage)
        }
    }
}