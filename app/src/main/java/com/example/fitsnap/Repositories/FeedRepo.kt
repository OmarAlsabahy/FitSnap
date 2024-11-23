package com.example.fitsnap.Repositories

import com.example.fitsnap.Api.FeedApi
import javax.inject.Inject

class FeedRepo @Inject constructor(private val api : FeedApi) {
    suspend fun getFeed() = api.getFeed()
}