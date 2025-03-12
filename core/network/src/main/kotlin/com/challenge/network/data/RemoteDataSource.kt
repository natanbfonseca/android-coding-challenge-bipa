package com.challenge.network.data

import com.challenge.network.model.LightningResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getNodes() : Flow<List<LightningResponse>>
}

