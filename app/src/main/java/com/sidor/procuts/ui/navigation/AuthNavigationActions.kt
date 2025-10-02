package com.sidor.procuts.ui.navigation

import kotlinx.serialization.Serializable

sealed class AuthRoute {
    @Serializable data object SignIn : AuthRoute()
    @Serializable data object SignUp : AuthRoute()
}