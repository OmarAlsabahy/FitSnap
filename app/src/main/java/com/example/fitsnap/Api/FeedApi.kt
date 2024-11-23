package com.example.fitsnap.Api

import com.example.fitsnap.Models.FoodModel
import retrofit2.http.GET

interface FeedApi {
    @GET("food_info/")
    suspend fun getFeed():FoodModel
}