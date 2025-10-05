package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sidor.procuts.data.ClientDTO
import com.sidor.procuts.data.CutDTO
import com.sidor.procuts.data.CutDateDTO
import com.sidor.procuts.data.CutDateInfoDTO
import com.sidor.procuts.data.CutDateRepository
import com.sidor.procuts.data.CutRepository
import com.sidor.procuts.data.defaultCutDTO
import com.sidor.procuts.data.defaultCutDateDTO
import com.sidor.procuts.ui.screens.screentypes.HomeScreenType
import com.sidor.procuts.ui.viewmodels.HomeViewModel.Companion.TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    val cutDateRepository: CutDateRepository,
    val cutRepository: CutRepository
) : ViewModel() {
    data class UiState(
        val screenType: HomeScreenType,
        val clientDTO: ClientDTO? = null,
        val cutDateDTO: CutDateDTO? = null,
        val cutDTO: CutDTO? = null,
    )

    init {
        viewModelScope.launch {
            cutDateRepository.loadCuts()
        }
    }

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState(HomeScreenType.Home))
    val uiState: StateFlow<UiState> get() = _uiState

    fun setClient(clientDTO: ClientDTO) {
        _uiState.value = _uiState.value.copy(clientDTO = clientDTO)
    }

    fun setVisit(visit: CutDateDTO) {
        _uiState.value = _uiState.value.copy(cutDateDTO = visit)
    }

    fun setCut(cut: CutDTO) {
        _uiState.value = _uiState.value.copy(cutDTO = cut)
    }

    fun getCutDTO(cutId: Int): StateFlow<CutDTO> =
        cutRepository
            .getCutStream(cutId)
            .map { it ?: defaultCutDTO }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = defaultCutDTO
            )


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

    fun addCutDate(cutDateInfoDTO: CutDateInfoDTO) {
        viewModelScope.launch {
            cutDateRepository.insertCut(cutDateInfoDTO)
        }
    }

    fun getClientCutDates(
        clientId: Int? = _uiState.value.clientDTO?.id,
        onComplete: () -> Unit = {}
    ): List<StateFlow<CutDateDTO>> =
        clientId?.let { cutDateRepository.getClientCutDatesStateFlows(
            clientId = it,
            scope = viewModelScope,
            onComplete = onComplete
        ) } ?: listOf()

    companion object {
        const val TIMEOUT_MILLIS = 5_000L
    }
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