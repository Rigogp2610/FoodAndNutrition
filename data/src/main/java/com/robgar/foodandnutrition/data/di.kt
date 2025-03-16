package com.robgar.foodandnutrition.data

import com.robgar.foodandnutrition.data.repository.IngredientsRepositoryImp
import com.robgar.foodandnutrition.domain.repository.IngredientsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DomainBindsModule {

    @Binds
    abstract fun bindIngredientsRepository(ingredientsRepositoryImp: IngredientsRepositoryImp) : IngredientsRepository
}