package com.robgar.foodandnutrition.framework.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteEstimatedCost(
    val value: Double,
    val unit: String
)