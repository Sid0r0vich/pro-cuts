package com.example.wellness.auth

data class AuthData(
    val email: String,
    val password: String
)

data class Credentials(
    val name: String,
    val email: String,
    val password: String
)
