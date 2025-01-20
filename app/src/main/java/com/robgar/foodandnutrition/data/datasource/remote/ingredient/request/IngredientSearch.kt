package com.robgar.foodandnutrition.data.datasource.remote.ingredient.request

data class IngredientSearch (
    val name: String,
    val number: Int,
    val offset: Int
)