package com.sidor.procuts.data

class Client(
    val id: Int,
    val firstName: String,
    val lastName: String = "",
    val middleName: String? = null,
    val photo: ByteArray? = null
) {
    fun getFullName(): String {
        return "$firstName ${middleName ?: ""} $lastName"
    }
}