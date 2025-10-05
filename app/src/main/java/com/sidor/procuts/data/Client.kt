package com.sidor.procuts.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ClientDTO(
    val id: Int = -1,
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("middle_name")
    val middleName: String? = null,
    val photo: ByteArray? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null
) {
    fun getFullName(): String = toPersonDTO().getFullName()

    fun toPersonDTO(): PersonDTO =
        PersonDTO(
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            photo = photo
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
    @Transient
    val photo: ByteArray? = null,
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