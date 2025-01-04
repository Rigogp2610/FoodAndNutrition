package com.robgar.foodandnutrition.data.datasource.remote

import com.robgar.foodandnutrition.data.datasource.remote.ingredient.RemoteIngredient
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RemoteIngredient>
)