package com.sidor.procuts.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sidor.procuts.ui.navigation.BottomNavigationBar
import com.sidor.procuts.ui.navigation.NavigationActions
import com.sidor.procuts.ui.navigation.Route
import com.sidor.procuts.ui.screens.PaddingProviderScreen
import com.sidor.procuts.ui.screens.routes.HomeRoute

@Composable
fun MyApp(modifier: Modifier) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface {
        Scaffold(
            bottomBar = {
                BottomNavigationBar(
                    currentScreen = currentDestination
                ) { route ->
                    navigationActions.navigateTo(route)
                }
            }
        ) {
            innerPadding ->
            PaddingProviderScreen {
                MyNavHost(
                    navController = navController,
                    navigationActions = navigationActions,
                    modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
                    pad = innerPadding
                )
            }
        }
    }
}

@Composable
private fun MyNavHost(
    navController: NavHostController,
    navigationActions: NavigationActions,
    modifier: Modifier = Modifier,
    pad: PaddingValues
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.Home,
    ) {
        composable<Route.Home> {
            HomeRoute()
        }
        composable<Route.Profile> {
        }
    }
}
