package com.sidor.procuts.data

import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    fun getClientStream(clientId: Int): Flow<ClientDTO?>
    fun getStream(): List<Flow<ClientDTO>>
    fun getClientWithPhoneNumber(phoneNumber: String): Int?
    fun insertClient(clientInfoDTO: ClientInfoDTO)
    fun updateClient(clientDTO: ClientDTO): Boolean
    fun deleteClient(clientId: Int)
}