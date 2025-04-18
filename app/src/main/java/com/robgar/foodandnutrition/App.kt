package com.robgar.foodandnutrition

import android.app.Application
import androidx.room.Room
import com.robgar.foodandnutrition.framework.database.FoodAndNutritionDatabase
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}