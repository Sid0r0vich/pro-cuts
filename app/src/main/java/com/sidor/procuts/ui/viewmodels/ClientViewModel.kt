package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.ClientRepository
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.CutDateRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ClientViewModel @Inject constructor(
    val clientRepository: ClientRepository,
    val cutDateRepository: CutDateRepository
) : ViewModel() {
    val cuts: MutableStateFlow<Map<Int, StateFlow<CutDateDTO>>> = MutableStateFlow(mapOf<Int, StateFlow<CutDateDTO>>())

    fun deleteClient(clientId: Int) {
        viewModelScope.launch {
            clientRepository.deleteClient(clientId)
        }
    }

    fun deleteCutDate(cutId: Int) {
        viewModelScope.launch {
            if(cutDateRepository.deleteCut(cutId)) {
                val newMap = cuts.value.toMutableMap()
                newMap.remove(cutId)
                cuts.value = newMap
            }
        }
    }

    fun getClientCutDates(
        clientId: Int?,
    ): List<StateFlow<CutDateDTO>> {
        val result = clientId?.let {
            cutDateRepository.getAllCutsWithClientId(it)
        } ?: listOf()

        cuts.value = result.associate { stateFlow -> stateFlow.value.id to stateFlow }.toMutableMap()
        return result
    }
}