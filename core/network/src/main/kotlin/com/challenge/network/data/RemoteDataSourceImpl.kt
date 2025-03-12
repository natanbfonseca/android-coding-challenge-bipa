package com.challenge.network.data

import com.challenge.network.model.LightningResponse
import com.challenge.network.service.LightningService
import com.challenge.network.util.safeApiCall
import kotlinx.coroutines.flow.Flow

internal class RemoteDataSourceImpl(
    private val remote: LightningService
) : RemoteDataSource {
    override fun getNodes(): Flow<List<LightningResponse>> =
        safeApiCall { remote.getNodes() }
}
