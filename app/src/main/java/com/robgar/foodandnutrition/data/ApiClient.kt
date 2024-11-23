package com.robgar.foodandnutrition.data

import com.robgar.foodandnutrition.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

object ApiClient {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(::apiKeyAsQuery)
        .build()

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val instance = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create<ApiService>()
}

private fun apiKeyAsQuery(chain: Interceptor.Chain) = chain.proceed(
    chain
        .request()
        .newBuilder()
        .url(
            chain.request().url
                .newBuilder()
                .addQueryParameter("apiKey", BuildConfig.SPOONACULAR_API_KEY)
                .build()
        ).build()
)