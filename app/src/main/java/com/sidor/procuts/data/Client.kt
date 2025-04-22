package com.sidor.procuts.data



class ClientDTO(
    val id: Int = -1,
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String? = null,
    val photo: ByteArray? = null
) {
    fun getFullName(): String {
        return "$firstName ${middleName ?: ""} $lastName"
    }
}

class ClientInfoDTO(
    val firstName: String,
    val lastName: String = "",
    val middleName: String? = null,
    val photo: ByteArray? = null
) {
    fun withId(clientId: Int): ClientDTO =
        ClientDTO(
            id = clientId,
            firstName = firstName,
            lastName = lastName,
            middleName = middleName,
            photo = photo
        )
}