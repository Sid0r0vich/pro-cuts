package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.cliensList
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

open class HomeViewModel : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val client: Client? = null,
        val cutDate: CutDate? = null,
        val cut: Cut? = null,
        val cutId: Int? = null
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClient(client: Client) {
        _uiState.value = _uiState.value.copy(client = client)
    }

    fun setVisit(visit: CutDate) {
        _uiState.value = _uiState.value.copy(cutDate = visit)
    }

    fun setCut(cut: Cut) {
        _uiState.value = _uiState.value.copy(cut = cut)
    }

    fun setCutId(cutId: Int) {
        _uiState.value = _uiState.value.copy(cutId = cutId)
    }

    fun navigate(screenType: HomeScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun navigateHome() = navigate(HomeScreenType.Home)
    fun navigateClients() = navigate(HomeScreenType.Clients)
    fun navigateClient() = navigate(HomeScreenType.Client)
    fun navigateEditClient() = navigate(HomeScreenType.EditClient)
    fun navigateAddClient() = navigate(HomeScreenType.AddClient)
    fun navigateVisit() = navigate(HomeScreenType.Visit)
    fun navigateCut() = navigate(HomeScreenType.Cut)
    fun navigateAddCut() = navigate(HomeScreenType.AddCut)
    fun navigateAddCare() = navigate(HomeScreenType.AddCare)

    fun addClient(client: Client) {
        cliensList[client.id] = client
    }

    fun getClient(): Client? {
        return _uiState.value.client
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}