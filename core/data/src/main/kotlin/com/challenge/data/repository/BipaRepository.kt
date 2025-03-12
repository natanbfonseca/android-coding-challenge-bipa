package com.challenge.data.repository

import com.challenge.model.Node
import kotlinx.coroutines.flow.Flow

interface BipaRepository {
    fun getNodes(): Flow<List<Node>>
}