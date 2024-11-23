package com.example.fitsnap.Modules

import com.example.fitsnap.Api.FeedApi
import com.example.fitsnap.Api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun getRetrofit():Retrofit{
        return Retrofit.
                Builder()
            .baseUrl("http://52.25.229.242:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getHomeApi(retrofit : Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun getFeedApi(retrofit: Retrofit):FeedApi{
        return retrofit.create(FeedApi::class.java)
    }


}