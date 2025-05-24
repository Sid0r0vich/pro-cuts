package com.sidor.procuts.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientMockRepository @Inject constructor(): ClientRepository {
    override fun getClientStream(clientId: Int): Flow<ClientDTO?> =
        flow {
            emit(clientsMap[clientId])
        }

    override fun getStream(): List<Flow<ClientDTO>> =
        clientsMap.map { (_, client) ->
            flow { emit(client) }
        }

    override fun getClientWithPhoneNumber(phoneNumber: String): Int? {
        Log.d("GET CLIENT", phoneNumber.toString())
        return clientsMap.filter { (_, client) -> client.phoneNumber == phoneNumber }
            .values
            .toList()
            .singleOrNull()
            ?.id
    }


    override fun insertClient(clientInfoDTO: ClientInfoDTO) {
        val clientId = clientsMap.size
        clientsMap[clientId] = clientInfoDTO.withId(clientId)
    }

    override fun updateClient(clientDTO: ClientDTO): Boolean {
        if (!clientsMap.contains(clientDTO.id)) {
            return false
        }

        clientsMap[clientDTO.id] = clientDTO
        return true
    }

    override fun deleteClient(clientId: Int) { TODO() }

    companion object {
        private var clientsMap = mutableListOf(
            ClientDTO(id = 0, firstName = "Jason", lastName = "Statham", phoneNumber = "8888888888"),
            ClientDTO(id = 1, firstName = "Иван", lastName = "Иванов", phoneNumber = "8888888889"),
            ClientDTO(id = 11, firstName = "Пётр", lastName = "Петров", phoneNumber = "8888888887"),
//            ClientDTO(id = 2, firstName = "Илья", middleName = "Игоревич", lastName =  "Муромцев"),
//            ClientDTO(id = 3, firstName = "Дмитрий", middleName = "Сергеевич", lastName = "Шалымов"),
//            ClientDTO(id = 4, firstName = "Владимир", lastName = "Путин"),
//            ClientDTO(id = 5, firstName = "Евгений", lastName =  "Туаев"),
//            ClientDTO(id = 6, firstName = "Роберт", lastName = "Смайт"),
//            ClientDTO(id = 7, firstName = "Павел", lastName = "Скаков"),
//            ClientDTO(id = 8, firstName = "Мистер", lastName = "Бист"),
//            ClientDTO(id = 9, firstName = "Dwayne", middleName =  "Douglas", lastName =  "Johnson"),
//            ClientDTO(id = 10, firstName = "Иван", lastName = "Петрухин"),
        ).associate { client ->
            client.id to client
        }.toMutableMap()
    }
}