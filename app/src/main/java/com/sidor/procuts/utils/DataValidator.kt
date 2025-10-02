package com.sidor.procuts.utils

import android.util.Patterns
import com.example.wellness.auth.AuthData
import com.example.wellness.auth.AuthStatus
import com.example.wellness.auth.Credentials

object DataValidator {
    enum class Status {
        SUCCESS,
        INVALID_EMAIL_FORMAT,
        INVALID_PASSWORD_FORMAT,
        INVALID_NAME_FORMAT
    }

    fun validateEmailFormat(email: String): Boolean
        = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun validatePasswordFormat(password: String): Boolean
        = password.length >= MIN_PASSWORD_LENGTH

    fun validateNameFormat(name: String): Boolean
        = name.length >= MIN_NAME_LENGTH

    fun validateLoginDataWithStatus(authData: AuthData): Status {
        if (!validateEmailFormat(authData.email))
            return Status.INVALID_EMAIL_FORMAT
        if (!validatePasswordFormat(authData.password))
            return Status.INVALID_PASSWORD_FORMAT

        return Status.SUCCESS
    }

    fun validateCredentialsWithStatus(credentials: Credentials): Status {
        if (!validateEmailFormat(credentials.email))
            return Status.INVALID_EMAIL_FORMAT
        if (!validatePasswordFormat(credentials.password))
            return Status.INVALID_PASSWORD_FORMAT
        if (!validateNameFormat(credentials.name))
            return Status.INVALID_NAME_FORMAT

        return Status.SUCCESS
    }

    fun validateAuthData(authData: AuthData): Boolean =
        validateLoginDataWithStatus(authData) == Status.SUCCESS

    private const val MIN_PASSWORD_LENGTH = 6
    private const val MIN_NAME_LENGTH = 4
}

fun DataValidator.Status.toAuthStatus(): AuthStatus {
    return when(this) {
        DataValidator.Status.SUCCESS -> AuthStatus.SUCCESS
        DataValidator.Status.INVALID_EMAIL_FORMAT -> AuthStatus.INVALID_EMAIL_FORMAT
        DataValidator.Status.INVALID_PASSWORD_FORMAT -> AuthStatus.INVALID_PASSWORD_FORMAT
        DataValidator.Status.INVALID_NAME_FORMAT -> AuthStatus.INVALID_NAME_FORMAT
    }
}