package com.robgar.foodandnutrition.framework.remote

import com.robgar.foodandnutrition.framework.remote.ingredient.RemoteIngredient
import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val offset: Int,
    val number: Int,
    val totalResults: Int,
    val results: List<RemoteIngredient>
)