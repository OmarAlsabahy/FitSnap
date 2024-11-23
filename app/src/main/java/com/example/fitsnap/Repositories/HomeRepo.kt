package com.example.fitsnap.Repositories

import com.example.fitsnap.Api.HomeApi
import com.example.fitsnap.Models.HomeModel
import javax.inject.Inject

class HomeRepo @Inject constructor(private val api : HomeApi) {
    suspend fun getHome():HomeModel{
        return api.getHomePage()
    }

}