package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.test.sample10Ingredients
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock


class SearchIngredientsByNameUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val ingredientsFlow = flowOf(sample10Ingredients())
        val useCase = SearchIngredientsByNameUseCase(mock {
            on { searchIngredientsByName("b", 10, 0) } doReturn ingredientsFlow
        })

        val result = useCase("b", 10, 0)

        assertEquals(ingredientsFlow, result)
    }
}