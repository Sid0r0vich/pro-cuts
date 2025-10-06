package com.sidor.procuts.data.models

class PersonDTO(
    val name: String,
    val photo: ByteArray? = null,
)

val defaultPerson: PersonDTO = defaultUser.toPersonDTO()