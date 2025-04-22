package com.sidor.procuts.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.ClientInfoDTO
import com.sidor.procuts.data.ClientRepository
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.data.CutDateDTO
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.CutDateRepository
import com.sidor.procuts.data.defaultClientDTO
import com.sidor.procuts.data.defaultCutDateDTO
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel.Companion.TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    val clientRepository: ClientRepository,
    val cutDateRepository: CutDateRepository
) : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val clientDTO: ClientDTO? = null,
        val cutDate: CutDateDTO? = null,
        val cut: CutDTO? = null,
    )

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClient(clientDTO: ClientDTO) {
        _uiState.value = _uiState.value.copy(clientDTO = clientDTO)
    }

    fun setVisit(visit: CutDateDTO) {
        _uiState.value = _uiState.value.copy(cutDate = visit)
    }

    fun setCut(cut: CutDTO) {
        _uiState.value = _uiState.value.copy(cut = cut)
    }

    fun getClientDTO() =
        _uiState.value.clientDTO

    fun getCutDateDTO() =
        _uiState.value.cutDate

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

    fun addClient(clientInfoDTO: ClientInfoDTO) {
        clientRepository.insertClient(clientInfoDTO)
    }

    fun editClient(clientDTO: ClientDTO) {
        clientRepository.updateClient(clientDTO)
    }

    fun getAllClients(
        onComplete: () -> Unit
    ) =
        clientRepository
            .getClientStateFlows(viewModelScope, onComplete)

    fun getClientIdOnPhoneNumber(phoneNumber: String): Int? =
        clientRepository.getClientWithPhoneNumber(phoneNumber)

    fun addCutDate(cutDateInfoDTO: CutDateInfoDTO) {
        cutDateRepository.insertCut(cutDateInfoDTO)
    }

    fun getClientCutDates(
        onComplete: () -> Unit
    ): List<StateFlow<CutDateDTO>> =
        _uiState.value.clientDTO?.id?.let { cutDateRepository.getClientCutDatesStateFlows(
            clientId = it,
            scope = viewModelScope,
            onComplete = onComplete
        ) } ?: listOf()

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
}

fun ClientRepository.getClientStateFlows(
    scope: CoroutineScope,
    onComplete: () -> Unit
): List<StateFlow<ClientDTO>> =
    this.getStream().map { flow ->
        flow.distinctUntilChanged()
            .onEach { value ->
                if (value != defaultClientDTO) {
                    onComplete()
                }
            }
            .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = defaultClientDTO
        )
    }

fun CutDateRepository.getClientCutDatesStateFlows(
    clientId: Int,
    scope: CoroutineScope,
    onComplete: () -> Unit
): List<StateFlow<CutDateDTO>> {
    return this.getAllCutsWithClientId(clientId = clientId).map { flow ->
        flow.distinctUntilChanged()
            .onEach { value ->
                if (value != defaultCutDateDTO) {
                    onComplete()
                }
            }
            .stateIn(
                scope = scope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = defaultCutDateDTO
            )
    }
}