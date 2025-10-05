package com.sidor.procuts.data

import com.example.wellness.auth.Auth
import com.sidor.procuts.network.db.ClientInfoWithUserIdDTO
import com.sidor.procuts.network.db.GinApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientDBRepository @Inject constructor(
    private val apiService: GinApiService,
    private val auth: Auth
): ClientRepository {
    private val clientsStateFlow =
        MutableStateFlow<MutableMap<Int, MutableStateFlow<ClientDTO>>>(mutableMapOf<Int, MutableStateFlow<ClientDTO>>())

    override fun getClientStream(clientId: Int): StateFlow<ClientDTO>? = clientsStateFlow.value[clientId]

    override suspend fun loadClients() {
        val userId = auth.userId.value ?: ""
        clientsStateFlow.value = apiService.getClients(userId).associate {
            client -> client.id to MutableStateFlow(client)
        }.toMutableMap()
    }

    override fun getClientsStateFlow(): MutableStateFlow<MutableMap<Int, MutableStateFlow<ClientDTO>>> {
        return clientsStateFlow
    }

    override suspend fun insertClient(clientInfoDTO: ClientInfoDTO) {
        val clientDto = apiService.createClient(
            ClientInfoWithUserIdDTO(
                firstName = clientInfoDTO.firstName,
                lastName = clientInfoDTO.lastName,
                middleName = clientInfoDTO.middleName,
                photo = clientInfoDTO.photo,
                phoneNumber = clientInfoDTO.phoneNumber,
                userId = auth.userId.value ?: ""
            )
        )
        val updatedMap = clientsStateFlow.value.toMutableMap()
        updatedMap[clientDto.id] = MutableStateFlow(clientDto)
        clientsStateFlow.value = updatedMap
    }

    override suspend fun updateClient(clientDTO: ClientDTO) {
        val clientInfoDTO = apiService.editClient(clientDTO.id, clientDTO.toClientInfoDTO())
        val updatedMap = clientsStateFlow.value.toMutableMap()
        updatedMap[clientInfoDTO.id] = MutableStateFlow(clientInfoDTO)
        clientsStateFlow.value = updatedMap
    }

    override suspend fun deleteClient(clientId: Int) { TODO() }
}