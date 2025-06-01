package com.sidor.procuts.data

class PersonDTO(
    val firstName: String = "",
    val lastName: String = "",
    val middleName: String? = null,
    val photo: ByteArray? = null,
) {
    fun getFullName(): String {
        return "$firstName ${middleName ?: ""} $lastName"
    }
}

val defaultPersonDTO =
    PersonDTO(
        firstName = "Иван",
        lastName = "Иванов",
        middleName = "Иванович",
    )