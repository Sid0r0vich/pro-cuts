package com.sidor.procuts.data

data class Client(
    val firstName: String,
    val lastName: String = "",
    val middleName: String? = null
)