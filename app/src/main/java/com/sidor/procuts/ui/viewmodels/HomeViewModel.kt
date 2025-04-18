package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.sidor.procuts.data.Client
import com.sidor.procuts.data.Cut
import com.sidor.procuts.data.CutDate
import com.sidor.procuts.data.allCuts
import com.sidor.procuts.data.cliensList
import com.sidor.procuts.data.cutDatesList
import com.sidor.procuts.data.cutNamesToId
import com.sidor.procuts.ui.CutForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class HomeScreenType {
    Home,
    Client,
    AddClient,
    Cut,
    AddCut,
    AddCare,
    Visit,
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

    fun navigate(screenType: HomeScreenType) {
        _uiState.value = _uiState.value.copy(screenType = screenType)
    }

    fun navigateHome() = navigate(HomeScreenType.Home)
    fun navigateClient() = navigate(HomeScreenType.Client)
    fun navigateAddClient() = navigate(HomeScreenType.AddClient)
    fun navigateVisit() = navigate(HomeScreenType.Visit)
    fun navigateCut() = navigate(HomeScreenType.Cut)
    fun navigateAddCut() = navigate(HomeScreenType.AddCut)
    fun navigateAddCare() = navigate(HomeScreenType.AddCare)

    fun addClient(client: Client) {
        cliensList.add(client)
    }

    fun addCut(cutForm: CutForm): Boolean {
        cutNamesToId[cutForm.name]?.let { cutId ->
            cutDatesList.add(CutDate(cutId = cutId, date = cutForm.date))
            return true
        }

        return false
    }

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}