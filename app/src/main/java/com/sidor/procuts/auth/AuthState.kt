package com.example.wellness.auth

sealed class AuthState {
    data object Authenticated: AuthState()
    data object Unauthenticated: AuthState()
    data object Loading: AuthState()
    data class Error(val status: AuthStatus): AuthState()
}