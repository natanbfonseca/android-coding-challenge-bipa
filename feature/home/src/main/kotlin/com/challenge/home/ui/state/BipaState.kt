package com.challenge.home.ui.state

import com.challenge.model.Node

data class BipaState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val nodes: List<Node> = emptyList()
)