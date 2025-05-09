package com.example.recipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.thecatapi.com/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val recipeService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 20,
        @Query("has_breeds") has_breeds: Int = 1,
        @Query("api_key") apiKey: String = "live_wYi9iC9n7ISWCwmc6vdYhud77iSxrIjrQkFiefv69ovKte0M9qImyaGAxJgWomjf"
    ): CatResponse
}