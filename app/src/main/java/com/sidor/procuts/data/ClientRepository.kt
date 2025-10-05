package com.sidor.procuts.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface ClientRepository {
    fun getClientStream(clientId: Int): StateFlow<ClientDTO>?
    suspend fun loadClients()
    fun getClientsStateFlow(): StateFlow<Map<Int, MutableStateFlow<ClientDTO>>>
    suspend fun insertClient(clientInfoDTO: ClientInfoDTO)
    suspend fun updateClient(clientDTO: ClientDTO)
    suspend fun deleteClient(clientId: Int)
}