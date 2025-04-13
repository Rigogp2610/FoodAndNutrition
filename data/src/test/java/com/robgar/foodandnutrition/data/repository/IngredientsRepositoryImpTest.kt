package com.robgar.foodandnutrition.data.repository

import com.robgar.foodandnutrition.data.datasource.IngredientsLocalDataSource
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import com.robgar.foodandnutrition.test.sample10Ingredients
import com.robgar.foodandnutrition.test.sampleIngredient
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class IngredientsRepositoryImpTest {

    @Mock
    lateinit var localDataSource: IngredientsLocalDataSource

    @Mock
    lateinit var remoteDataSource: IngredientsRemoteDataSource

    private lateinit var ingredientsRepository: IngredientsRepository

    @Before
    fun setUp() {
        ingredientsRepository = IngredientsRepositoryImp(
            localDataSource,
            remoteDataSource
        )
    }

    @Test
    fun `Get ingredients from local data when available`() = runBlocking {
        // Given
        val localIngredients = sample10Ingredients()
        whenever(localDataSource.findIngredientsByPrefix("b", 10, 0)).thenReturn(flowOf(localIngredients))

        // When
        val result = ingredientsRepository.searchIngredientsByName("b", 10, 0)

        // Then
        assertEquals(localIngredients, result.first())
    }

    @Test
    fun `Save ingredients to Room when local data is empty`() : Unit = runBlocking {
        // Given
        val localIngredients = emptyList<Ingredient>()
        val remoteIngredients = sample10Ingredients()
        whenever(localDataSource.findIngredientsByPrefix("b", 10, 0)).thenReturn(flowOf(localIngredients))
        whenever(remoteDataSource.searchIngredientsByName("b", 10, 0)).thenReturn(remoteIngredients)

        // When
        ingredientsRepository.searchIngredientsByName("b", 10, 0).first()

        // Then
        verify(localDataSource).saveIngredients(remoteIngredients, "b")
    }

    @Test
    fun `fetch information of ingredient when pass ingredient id`(): Unit = runBlocking {
        // Given
        val ingredient = sampleIngredient(1, 123, "banana")
        whenever(remoteDataSource.fetchInformationOfIngredient(1)).thenReturn(ingredient)

        // When
        val result = ingredientsRepository.fetchInformationOfIngredient(1)

        // Then
        assertEquals(ingredient, result.first())
    }






}