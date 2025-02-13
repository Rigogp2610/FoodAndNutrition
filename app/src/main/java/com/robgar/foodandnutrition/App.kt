package com.robgar.foodandnutrition

import android.app.Application
import androidx.room.Room
import com.robgar.foodandnutrition.framework.database.FoodAndNutritionDatabase

class App : Application() {

    lateinit var db: FoodAndNutritionDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(this, FoodAndNutritionDatabase::class.java, "food-and-nutrition-db")
            .build()
    }
}