package com.sidor.procuts.ui.screens.auth

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
import com.sidor.procuts.ui.navigation.AuthNavigationActions
import com.sidor.procuts.ui.navigation.AuthRoute
import com.sidor.procuts.ui.screens.PaddingProviderScreen

@Composable
fun AuthScreen(
    onSignUpClick: () -> Unit
) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        AuthNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Surface {
        PaddingProviderScreen {
            AuthNavHost(
                navController = navController,
                navigationActions = navigationActions,
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
private fun AuthNavHost(
    navController: NavHostController,
    navigationActions: AuthNavigationActions,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AuthRoute.SignUp,
    ) {
        composable<AuthRoute.SignUp> {
            RegistrationScreen(
                onSignUpClick = onSignUpClick
            )
        }
    }
}
