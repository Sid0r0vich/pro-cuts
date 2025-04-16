package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class HomeScreenType {
    Home,
    Client,
    Cut
}

open class HomeViewModel : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val clientName: String? = null,
        val cutName: String? = null,
        val cutImgId: Int? = null
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClientName(clientName: String) {
        _uiState.value = _uiState.value.copy(clientName = clientName)
    }

    fun setCutName(cutName: String) {
        _uiState.value = _uiState.value.copy(cutName = cutName)
    }

    fun setCutImgId(imgId: Int) {
        _uiState.value = _uiState.value.copy(cutImgId = imgId)
    }

    fun navigateHome() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Home)
    }

    fun navigateClient() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Client)
    }

    fun navigateCut() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Cut)
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}