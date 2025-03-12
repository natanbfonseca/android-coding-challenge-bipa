package com.challenge.data.repository

import com.challenge.data.mapper.mapToNodes
import com.challenge.model.Node
import com.challenge.network.data.RemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

internal class BipaRepositoryImpl(
    private val remote: RemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : BipaRepository {
    override fun getNodes(): Flow<List<Node>> =
        remote
            .getNodes()
            .map { it.mapToNodes() }
            .flowOn(ioDispatcher)
}