package com.challenge.home.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.challenge.common.viewmodel.BaseViewModel
import com.challenge.domain.usecase.GetNodesUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class BipaViewModel(private val getNodesUseCase: GetNodesUseCase) :
    BaseViewModel<BipaState, BipaEvent>(BipaState()) {

    override fun handleEvent(event: BipaEvent) {
        when (event) {
            BipaEvent.LoadNodes -> loadNodes()
        }
    }

    private fun loadNodes() {
        viewModelScope.launch {
            getNodesUseCase()
                .onStart { updateUiState { copy(isLoading = true, error = null) } }
                .catch { updateUiState { copy(isLoading = false, error = it.message) } }
                .collect { result ->
                    updateUiState { copy(isLoading = false, nodes = result) }
                }
        }
    }
}