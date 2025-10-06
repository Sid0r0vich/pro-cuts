package com.sidor.procuts.utils

import com.sidor.procuts.data.models.ClientDTO
import kotlin.collections.filter

fun filterClients(
    clients: List<ClientDTO>,
    searchText: String
): List<ClientDTO> {
    return clients.filter { client ->
        if (searchText.isNotEmpty()) {
            client
                .getFullName()
                .lowercase()
                .contains(searchText.lowercase())
        } else true
    }
}