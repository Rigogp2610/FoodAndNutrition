package com.robgar.foodandnutrition.ui

import androidx.lifecycle.SavedStateHandle
import com.robgar.foodandnutrition.ui.screens.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @Named("ingredientId")
    fun provideIngredientId(savedStateHandle: SavedStateHandle): Int {
        return savedStateHandle[NavArgs.IngredientId.key] ?: throw IllegalArgumentException("IngredientId not found")
    }
}