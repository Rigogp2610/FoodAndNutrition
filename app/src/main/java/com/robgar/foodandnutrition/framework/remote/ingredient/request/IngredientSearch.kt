package com.robgar.foodandnutrition.framework.remote.ingredient.request

data class IngredientSearch (
    val name: String,
    val number: Int,
    val offset: Int
)