package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class HomeScreenType {
    Home,
    Client
}

open class HomeViewModel : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val clientName: String? = null
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClientName(clientName: String) {
        _uiState.value = _uiState.value.copy(clientName = clientName)
    }

    fun navigateHome() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Home)
    }

    fun navigateClient() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Client)
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}