package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class EditClientViewModel @Inject constructor(
    val clientRepository: ClientRepository,
) : ViewModel() {
    fun editClient(clientDTO: ClientDTO) {
        viewModelScope.launch {
            clientRepository.updateClient(clientDTO)
        }
    }
}