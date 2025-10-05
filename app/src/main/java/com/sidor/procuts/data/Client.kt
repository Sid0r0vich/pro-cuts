package com.sidor.procuts.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Serializable
class ClientDTO(
    val id: Int = -1,
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("middle_name")
    val middleName: String? = null,
    @SerialName("photo")
    val photo: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null
) {
    fun getFullName(): String = toPersonDTO().getFullName()

    @OptIn(ExperimentalEncodingApi::class)
    fun toPersonDTO(): PersonDTO =
        PersonDTO(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            photo = Base64.decode(photo ?: "")
        )

    fun toClientInfoDTO(): ClientInfoDTO =
        ClientInfoDTO(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            photo = photo,
            phoneNumber = phoneNumber
        )
}

@Serializable
class ClientInfoDTO(
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("middle_name")
    val middleName: String? = null,
    @SerialName("photo")
    val photo: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null
) {
    fun withId(clientId: Int): ClientDTO =
        ClientDTO(
            id = clientId,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            photo = photo,
            phoneNumber = phoneNumber
        )
}