package com.robgar.foodandnutrition.domain.model


data class Ingredient(
    val id: Int,
    val ingredientId: Int,
    val name: String,
    val image: String,
    val original: String? = null,
    val originalName: String? = null,
    val amount: Double? = null,
    val unit: String? = null,
    val unitShort: String? = null,
    val unitLong: String? = null,
    val possibleUnits: List<String>? = null,
    val consistency: String? = null,
    val shoppingListUnits: List<String>? = null,
    val aisle: String? = null,
    val meta: List<String>? = null,
    val caloricBreakdown: CaloricBreakdown? = null,
    val categoryPath: List<String>? = null,
    val queryTracking: String = ""
)

enum class ImageSize(val size: String) {
    SMALL("100x100"),
    MEDIUM("250x250"),
    BIG("500x500")
}

fun Ingredient.imagePath(size: ImageSize = ImageSize.MEDIUM) : String {
    return "https://img.spoonacular.com/ingredients_${size.size}/${image}"
}