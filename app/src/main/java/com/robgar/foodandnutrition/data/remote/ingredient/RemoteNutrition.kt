package com.robgar.foodandnutrition.data.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteNutrition(
    val nutrients: List<RemoteNutrient>,
    val properties: List<RemoteProperty>,
    val flavonoids: List<RemoteFlavonoid>,
    val caloricBreakdown: RemoteCaloricBreakdown,
    val weightPerServing: RemoteWeightPerServing
)