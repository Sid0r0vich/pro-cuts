package com.sidor.procuts.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.sidor.procuts.R
import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Home : Route
    @Serializable data object Profile : Route
}

data class TopLevelDestination(
    val route: Route,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class NavigationActions(private val navController: NavHostController) {
    fun upPress() {
        navController.navigateUp()
    }

    fun navigateTo(destination: Route) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    TopLevelDestination(
        route = Route.Home,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        iconTextId = R.string.tab_home
    ),
    TopLevelDestination(
        route = Route.Profile,
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person,
        iconTextId = R.string.tab_profile
    ),
)
