package com.robgar.foodandnutrition

import android.app.Application
import androidx.room.Room
import com.robgar.foodandnutrition.framework.database.FoodAndNutritionDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application()