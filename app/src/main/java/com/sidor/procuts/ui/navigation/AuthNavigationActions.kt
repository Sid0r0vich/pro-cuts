package com.sidor.procuts.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

sealed interface AuthRoute {
    @Serializable data object SignIn : MainRoute
    @Serializable data object SignUp : MainRoute
}

class AuthNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: AuthRoute) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}