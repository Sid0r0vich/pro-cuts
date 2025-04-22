package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

interface ClientRepository {
    fun getClientStream(clientId: Int): Flow<ClientDTO?>
    fun getStream(): List<Flow<ClientDTO>>
    fun insertClient(clientInfoDTO: ClientInfoDTO)
    fun updateClient(clientDTO: ClientDTO): Boolean
    fun deleteClient(clientId: Int)
}