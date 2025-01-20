package com.robgar.foodandnutrition.data.datasource.remote.ingredient

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteIngredient(
    @SerialName("id") val ingredientId: Int,
    val name: String,
    val image: String,
    val original: String? = null,
    val originalName: String? = null,
    val amount: Double? = null,
    val unit: String? = null,
    val unitShort: String? = null,
    val unitLong: String? = null,
    val possibleUnits: List<String>? = null,
    val estimatedCost: RemoteEstimatedCost? = null,
    val consistency: String? = null,
    val shoppingListUnits: List<String>? = null,
    val aisle: String? = null,
    val meta: List<String>? = null,
    val nutrition: RemoteNutrition? = null,
    val categoryPath: List<String>? = null
)