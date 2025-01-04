package com.robgar.foodandnutrition.data.datasource.remote.ingredient

import kotlinx.serialization.Serializable

@Serializable
data class RemoteProperty(
    val name: String,
    val amount: Double,
    val unit: String
)