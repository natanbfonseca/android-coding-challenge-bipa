package com.challenge.domain.usecase

import com.challenge.data.repository.BipaRepository
import com.challenge.model.Node
import kotlinx.coroutines.flow.Flow

fun interface GetNodesUseCase: () -> Flow<List<Node>>

internal class GetNodesUseCaseImpl(private val repository: BipaRepository): GetNodesUseCase {
    override  fun invoke(): Flow<List<Node>> {
        return repository.getNodes()
    }
}