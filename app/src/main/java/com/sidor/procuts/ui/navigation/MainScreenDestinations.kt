package com.sidor.procuts.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.sidor.procuts.R

data class MainScreenDestination(
    val route: MainRoute,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

val MAIN_SCREEN_DESTINATIONS = listOf(
    MainScreenDestination(
        route = MainRoute.Home,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Default.Home,
        iconTextId = R.string.tab_home
    ),
    MainScreenDestination(
        route = MainRoute.Profile,
        selectedIcon = Icons.Default.Person,
        unselectedIcon = Icons.Default.Person,
        iconTextId = R.string.tab_profile
    ),
)
