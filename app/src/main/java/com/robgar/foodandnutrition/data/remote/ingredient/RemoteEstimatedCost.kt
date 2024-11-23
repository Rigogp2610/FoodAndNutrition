package com.robgar.foodandnutrition.data.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteEstimatedCost(
    val value: Double,
    val unit: String
)