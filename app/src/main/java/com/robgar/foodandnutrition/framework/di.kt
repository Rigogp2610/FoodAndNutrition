package com.robgar.foodandnutrition.framework

import android.app.Application
import androidx.room.Room
import com.robgar.foodandnutrition.BuildConfig
import com.robgar.foodandnutrition.data.datasource.IngredientsLocalDataSource
import com.robgar.foodandnutrition.data.datasource.IngredientsRemoteDataSource
import com.robgar.foodandnutrition.framework.database.FoodAndNutritionDatabase
import com.robgar.foodandnutrition.framework.remote.ApiClient
import com.robgar.foodandnutrition.framework.remote.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey() = BuildConfig.SPOONACULAR_API_KEY

    @Provides
    @Singleton
    @Named("apiUrl")
    fun provideBaseUrl() = BuildConfig.SPOONACULAR_API_URL
}

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(app, FoodAndNutritionDatabase::class.java, "food-and-nutrition-db").build()

    @Provides
    fun provideIngredientsDao(database: FoodAndNutritionDatabase) = database.ingredientsDao()

    @Provides
    @Singleton
    fun provideIngredientsClient(@Named("apiKey") apiKey: String, @Named("apiUrl") apiUrl: String) : ApiService = ApiClient(apiKey, apiUrl).instance
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkBindsModule {

    @Binds
    abstract fun bindIngredientsLocalDataSource(ingredientsLocalDataSourceImpl: IngredientsLocalDataSourceImp): IngredientsLocalDataSource

    @Binds
    abstract fun bindIngredientsRemoteDataSource(ingredientsRemoteDataSourceImpl: IngredientsRemoteDataSourceImp): IngredientsRemoteDataSource
}