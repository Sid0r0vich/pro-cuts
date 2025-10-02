package com.sidor.procuts.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

sealed interface MainRoute {
    @Serializable data object Home : MainRoute
    @Serializable data object Profile : MainRoute
}

class MainNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: MainRoute) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}