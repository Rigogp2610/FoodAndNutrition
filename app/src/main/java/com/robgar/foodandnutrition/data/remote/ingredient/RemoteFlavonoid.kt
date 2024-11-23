package com.robgar.foodandnutrition.data.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteFlavonoid(
    val name: String,
    val amount: Double,
    val unit: String
)