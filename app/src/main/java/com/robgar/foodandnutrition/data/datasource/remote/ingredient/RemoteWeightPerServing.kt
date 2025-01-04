package com.robgar.foodandnutrition.data.datasource.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteWeightPerServing(
    val amount: Int,
    val unit: String
)