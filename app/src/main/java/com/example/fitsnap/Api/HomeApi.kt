package com.example.fitsnap.Api

import com.example.fitsnap.Models.HomeModel
import retrofit2.http.GET

interface HomeApi {
    @GET("homepage_v2/")
    suspend fun getHomePage():HomeModel
}