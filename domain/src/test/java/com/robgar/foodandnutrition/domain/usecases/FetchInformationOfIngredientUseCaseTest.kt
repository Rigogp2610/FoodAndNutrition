package com.robgar.foodandnutrition.domain.usecases

import com.robgar.foodandnutrition.test.sampleIngredient
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FetchInformationOfIngredientUseCaseTest {

    @Test
    fun `Invoke calls repository`() {
        val ingredientFlow = flowOf(sampleIngredient(1, 123, "banana"))
        val useCase = FetchInformationOfIngredientUseCase(mock {
            on { fetchInformationOfIngredient(1) } doReturn ingredientFlow
        })

        val result = useCase(1)

        assertEquals(ingredientFlow, result)

    }
}