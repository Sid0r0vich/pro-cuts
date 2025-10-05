package com.sidor.procuts.network.db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val name: String
)

@Serializable
class ClientInfoWithUserIdDTO(
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    @SerialName("middle_name")
    val middleName: String? = null,
    @SerialName("photo")
    val photo: String? = null,
    @SerialName("phone_number")
    val phoneNumber: String? = null,
    @SerialName("user_id")
    val userId: String
)