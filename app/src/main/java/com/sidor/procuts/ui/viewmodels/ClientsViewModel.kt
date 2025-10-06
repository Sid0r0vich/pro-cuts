package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ClientsViewModel @Inject constructor(
    val clientRepository: ClientRepository,
) : ViewModel() {
    val clients: StateFlow<Map<Int, StateFlow<ClientDTO>>> = clientRepository.getClientsStateFlow()

    init {
        viewModelScope.launch {
            clientRepository.loadClients()
        }
    }
}