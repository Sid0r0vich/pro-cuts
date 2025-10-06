package com.sidor.procuts.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sidor.procuts.data.models.ClientInfoDTO
import com.sidor.procuts.data.ClientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AddClientViewModel @Inject constructor(
    val clientRepository: ClientRepository,
) : ViewModel() {
    fun addClient(clientInfoDTO: ClientInfoDTO) {
        viewModelScope.launch {
            clientRepository.insertClient(clientInfoDTO)
        }
    }
}