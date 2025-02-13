package com.robgar.foodandnutrition.framework

import com.robgar.foodandnutrition.domain.model.CaloricBreakdown
import com.robgar.foodandnutrition.framework.database.ingredient.IngredientDB
import com.robgar.foodandnutrition.framework.remote.ingredient.RemoteIngredient
import com.robgar.foodandnutrition.domain.model.Ingredient
import com.robgar.foodandnutrition.framework.remote.ingredient.RemoteCaloricBreakdown

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
            consistency,
            shoppingListUnits,
            aisle,
            meta,
            nutrition?.caloricBreakdown?.toDomain(),
            categoryPath
        )

    fun Ingredient.toDatabase(queryTracking: String): IngredientDB {
        return IngredientDB(id, ingredientId, name, image, queryTracking)
    }

    fun IngredientDB.toDomain(): Ingredient {
        return Ingredient(id, ingredientId, name, image, queryTracking)
    }

    fun RemoteCaloricBreakdown.toDomain(): CaloricBreakdown {
        return CaloricBreakdown(percentProtein, percentFat, percentCarbs)
    }
}