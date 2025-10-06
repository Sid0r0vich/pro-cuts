package com.sidor.procuts.data

import com.sidor.procuts.data.models.ClientDTO
import com.sidor.procuts.data.models.ClientInfoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ClientRepository {
    fun getClientStream(clientId: Int): StateFlow<ClientDTO>?
    suspend fun loadClients(): Boolean
    fun getClientsStateFlow(): StateFlow<Map<Int, MutableStateFlow<ClientDTO>>>
    suspend fun insertClient(clientInfoDTO: ClientInfoDTO): Boolean
    suspend fun updateClient(clientDTO: ClientDTO): Boolean
    suspend fun deleteClient(clientId: Int): Boolean
}