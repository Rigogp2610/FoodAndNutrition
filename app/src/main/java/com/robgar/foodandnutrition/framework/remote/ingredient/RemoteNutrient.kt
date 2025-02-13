package com.robgar.foodandnutrition.framework.remote.ingredient

import kotlinx.serialization.Serializable


@Serializable
data class RemoteNutrient(
    val name: String,
    val amount: Double,
    val unit: String,
    val percentOfDailyNeeds: Double
)