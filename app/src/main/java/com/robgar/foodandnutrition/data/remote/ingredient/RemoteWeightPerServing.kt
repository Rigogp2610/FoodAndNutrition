package com.robgar.foodandnutrition.data.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeightPerServing(
    val amount: Int,
    val unit: String
)