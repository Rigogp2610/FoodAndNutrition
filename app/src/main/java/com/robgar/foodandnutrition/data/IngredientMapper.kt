package com.robgar.foodandnutrition.data

import com.robgar.foodandnutrition.data.datasource.database.ingredient.IngredientDB
import com.robgar.foodandnutrition.data.datasource.remote.ingredient.RemoteIngredient

object IngredientMapper {

    fun RemoteIngredient.toDomainModel(): Ingredient =
        Ingredient(
            0,
            ingredientId,
            name,
            image,
            original,
            originalName,
            amount,
            unit,
            unitShort,
            unitLong,
            possibleUnits,
            estimatedCost,
            consistency,
            shoppingListUnits,
            aisle,
            meta,
            nutrition,
            categoryPath
        )

    fun Ingredient.toDatabase(queryTracking: String): IngredientDB {
        return IngredientDB(id, ingredientId, name, image, queryTracking)
    }

    fun IngredientDB.toModel(): Ingredient {
        return Ingredient(id, ingredientId, name, image, queryTracking)
    }
}