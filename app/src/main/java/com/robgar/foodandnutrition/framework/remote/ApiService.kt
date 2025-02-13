package com.robgar.foodandnutrition.framework.remote

import com.robgar.foodandnutrition.framework.remote.RemoteResult
import com.robgar.foodandnutrition.framework.remote.ingredient.RemoteIngredient
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("food/ingredients/search")
    suspend fun searchIngredientsByName(@Query("query") name: String, @Query("number") number: Int, @Query("offset") offset: Int) : RemoteResult

    @GET("food/ingredients/{id}/information")
    suspend fun fetchInformationOfIngredient(@Path("id") id: Int, @Query("amount") amount: Int) : RemoteIngredient
}