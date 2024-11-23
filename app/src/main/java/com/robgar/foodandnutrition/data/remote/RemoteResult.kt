package com.robgar.foodandnutrition.data.remote

import com.robgar.foodandnutrition.data.remote.ingredient.RemoteIngredient
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RemoteIngredient>
)