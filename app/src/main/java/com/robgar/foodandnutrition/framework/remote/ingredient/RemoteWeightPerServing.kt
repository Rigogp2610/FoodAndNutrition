package com.robgar.foodandnutrition.framework.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeightPerServing(
    val amount: Int,
    val unit: String
)