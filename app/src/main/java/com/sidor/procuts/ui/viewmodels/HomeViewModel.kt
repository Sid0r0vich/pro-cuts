package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class HomeScreenType {
    Home,
    Client,
    Visit,
    Cut
}

open class HomeViewModel : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val clientName: String? = null,
        val visit: CutDate? = null,
        val cut: Cut? = null
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClientName(clientName: String) {
        _uiState.value = _uiState.value.copy(clientName = clientName)
    }

    fun setVisit(visit: CutDate) {
        _uiState.value = _uiState.value.copy(visit = visit)
    }

    fun setCut(cut: Cut) {
        _uiState.value = _uiState.value.copy(cut = cut)
    }

    fun navigateHome() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Home)
    }

    fun navigateClient() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Client)
    }

    fun navigateVisit() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Visit)
    }

    fun navigateCut() {
        _uiState.value = _uiState.value.copy(screenType = HomeScreenType.Cut)
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}