package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class ClientViewModel @Inject constructor(
    val clientRepository: ClientRepository,
) : ViewModel() {
    fun deleteClient(clientId: Int) {
        viewModelScope.launch {
            clientRepository.deleteClient(clientId)
        }
    }
}