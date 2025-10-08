package com.sidor.procuts.ui.screens.main

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
import com.sidor.procuts.ui.navigation.MainNavigationActions
import com.sidor.procuts.ui.navigation.MainRoute
import com.sidor.procuts.ui.screens.PaddingProviderScreen
import com.sidor.procuts.ui.screens.routes.HomeRoute
import com.sidor.procuts.ui.screens.routes.UserProfileRoute

@Composable
fun MainScreen(
) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        MainNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentScreen = currentDestination
            ) { route ->
                navigationActions.navigateTo(route)
            }
        }
    ) { innerPadding ->
        PaddingProviderScreen {
            MainNavHost(
                navController = navController,
                modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            )
        }
    }
}

@Composable
private fun MainNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = MainRoute.Home,
    ) {
        composable<MainRoute.Home> {
            HomeRoute()
        }
        composable<MainRoute.Profile> {
            UserProfileRoute()
        }
    }
}
