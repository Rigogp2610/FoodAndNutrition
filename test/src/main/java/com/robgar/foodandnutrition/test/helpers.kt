package com.robgar.foodandnutrition.test

import com.robgar.foodandnutrition.domain.model.CaloricBreakdown
import com.robgar.foodandnutrition.domain.model.Ingredient

fun sampleIngredient(id: Int, ingredientId: Int, name: String, queryTracking: String = "") = Ingredient(
    id = id,
    ingredientId = ingredientId,
    name = name,
    image = "",
    original = name,
    originalName = name,
    amount = 1.0,
    unit = "piece",
    unitShort = "pcs",
    unitLong = "pieces",
    possibleUnits = listOf("piece", "slices", "grams"),
    consistency = "solid",
    shoppingListUnits = listOf("grams", "pieces"),
    aisle = "Fruits",
    meta = listOf("ripe", "yellow"),
    caloricBreakdown = CaloricBreakdown(
        percentProtein = 1.0,
        percentFat = 0.5,
        percentCarbs = 98.5
    ),
    categoryPath = listOf("Fruits", "Tropical fruits"),
    queryTracking = queryTracking
)

fun sampleIngredients(vararg data: Triple<Int, Int, String>, queryTracking: String = ""): List<Ingredient> {
    return data.map { sampleIngredient(id = it.first, ingredientId = it.second, name = it.third, queryTracking = queryTracking) }
}

fun sampleIngredients(vararg data: Ingredient): List<Ingredient> {
    return data.map { sampleIngredient(id = it.id, ingredientId = it.ingredientId, name = it.name, queryTracking = it.queryTracking) }
}

fun sample10Ingredients(): List<Ingredient> {
    return sampleIngredients(
        Ingredient(id = 1, ingredientId = 123, name = "banana", image = "", queryTracking = "ba"),
        Ingredient(id = 2, ingredientId = 124, name = "blueberry", image = "", queryTracking = "b"),
        Ingredient(id = 3, ingredientId = 125, name = "broccoli", image = "", queryTracking = "b"),
        Ingredient(id = 4, ingredientId = 126, name = "bread", image = "", queryTracking = "b"),
        Ingredient(id = 5, ingredientId = 127, name = "butter", image = "", queryTracking = "b"),
        Ingredient(id = 6, ingredientId = 128, name = "bacon", image = "", queryTracking = "ba"),
        Ingredient(id = 7, ingredientId = 129, name = "basil", image = "", queryTracking = "ba"),
        Ingredient(id = 8, ingredientId = 133, name = "beans", image = "", queryTracking = "b"),
        Ingredient(id = 9, ingredientId = 134, name = "brownie", image = "", queryTracking = "b"),
        Ingredient(id = 10, ingredientId = 135, name = "butternut squash", image = "", queryTracking = "b"),
    )
}

fun sample30Ingredients(): List<Ingredient> {
    return sampleIngredients(
        Ingredient(id = 1, ingredientId = 123, name = "banana", image = "", queryTracking = "ba"),
        Ingredient(id = 2, ingredientId = 124, name = "blueberry", image = "", queryTracking = "b"),
        Ingredient(id = 3, ingredientId = 125, name = "broccoli", image = "", queryTracking = "b"),
        Ingredient(id = 4, ingredientId = 126, name = "bread", image = "", queryTracking = "b"),
        Ingredient(id = 5, ingredientId = 127, name = "butter", image = "", queryTracking = "b"),
        Ingredient(id = 6, ingredientId = 128, name = "bacon", image = "", queryTracking = "ba"),
        Ingredient(id = 7, ingredientId = 129, name = "basil", image = "", queryTracking = "ba"),
        Ingredient(id = 8, ingredientId = 133, name = "beans", image = "", queryTracking = "b"),
        Ingredient(id = 9, ingredientId = 134, name = "brownie", image = "", queryTracking = "b"),
        Ingredient(id = 10, ingredientId = 135, name = "butternut squash", image = "", queryTracking = "b"),
        Ingredient(id = 11, ingredientId = 136, name = "beetroot", image = "", queryTracking = "b"),
        Ingredient(id = 12, ingredientId = 137, name = "bell pepper", image = "", queryTracking = "b"),
        Ingredient(id = 13, ingredientId = 138, name = "blackberry", image = "", queryTracking = "b"),
        Ingredient(id = 14, ingredientId = 139, name = "cabbage", image = "", queryTracking = "c"),
        Ingredient(id = 15, ingredientId = 140, name = "carrot", image = "", queryTracking = "c"),
        Ingredient(id = 16, ingredientId = 141, name = "cauliflower", image = "", queryTracking = "c"),
        Ingredient(id = 17, ingredientId = 142, name = "celery", image = "", queryTracking = "c"),
        Ingredient(id = 18, ingredientId = 143, name = "chickpea", image = "", queryTracking = "c"),
        Ingredient(id = 19, ingredientId = 144, name = "chili pepper", image = "", queryTracking = "c"),
        Ingredient(id = 20, ingredientId = 145, name = "cucumber", image = "", queryTracking = "c"),
        Ingredient(id = 21, ingredientId = 146, name = "eggplant", image = "", queryTracking = "e"),
        Ingredient(id = 22, ingredientId = 147, name = "garlic", image = "", queryTracking = "g"),
        Ingredient(id = 23, ingredientId = 148, name = "ginger", image = "", queryTracking = "g"),
        Ingredient(id = 24, ingredientId = 149, name = "green bean", image = "", queryTracking = "g"),
        Ingredient(id = 25, ingredientId = 150, name = "lettuce", image = "", queryTracking = "l"),
        Ingredient(id = 26, ingredientId = 151, name = "mushroom", image = "", queryTracking = "m"),
        Ingredient(id = 27, ingredientId = 152, name = "onion", image = "", queryTracking = "o"),
        Ingredient(id = 28, ingredientId = 153, name = "peas", image = "", queryTracking = "p"),
        Ingredient(id = 29, ingredientId = 154, name = "coconut", image = "", queryTracking = "co"),
        Ingredient(id = 30, ingredientId = 155, name = "spinach", image = "", queryTracking = "s")
    )
}