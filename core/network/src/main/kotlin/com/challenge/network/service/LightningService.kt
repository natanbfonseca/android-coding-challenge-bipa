package com.challenge.network.service

import com.challenge.network.model.LightningResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

internal interface LightningService {
    @Headers("Content-Type: application/json")
    @GET("nodes/rankings/connectivity")
    suspend fun getNodes(): Response<List<LightningResponse>>
}
