package com.example.wellness.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AuthUiState(
    name: MutableState<String> = mutableStateOf(""),
    email: MutableState<String> = mutableStateOf(""),
    password: MutableState<String> = mutableStateOf(""),
    nameIsValidated: MutableState<Boolean> = mutableStateOf(false),
    emailIsValidated: MutableState<Boolean> = mutableStateOf(false),
    passwordIsValidated: MutableState<Boolean> = mutableStateOf(false),
) {
    var name by name
    var email by email
    var password by password
    var nameIsValidated by nameIsValidated
    var emailIsValidated by emailIsValidated
    var passwordIsValidated by passwordIsValidated
}
