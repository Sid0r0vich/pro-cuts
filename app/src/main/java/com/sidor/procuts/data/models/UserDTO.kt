package com.sidor.procuts.data.models

import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
data class UserDTO(
    val id: String,
    val name: String,
    val photo: String?
) {
    @OptIn(ExperimentalEncodingApi::class)
    fun toPersonDTO() =
        PersonDTO(
            name = name,
            photo = photo?.let { Base64.decode(it) }
        )
}

val defaultUser = UserDTO(
    id = "-1",
    name = "Unauthorized",
    photo = null
)