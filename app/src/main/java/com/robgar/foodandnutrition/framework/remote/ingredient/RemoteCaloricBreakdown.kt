package com.robgar.foodandnutrition.framework.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteCaloricBreakdown(
    val percentProtein: Double,
    val percentFat: Double,
    val percentCarbs: Double
)