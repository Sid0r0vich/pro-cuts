package com.sidor.procuts.ui.screens.auth

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.ui.navigation.AuthRoute
import com.sidor.procuts.ui.screens.PaddingProviderScreen
import com.sidor.procuts.ui.viewmodels.AuthViewModel

@Composable
fun AuthScreen(
    viewModel: AuthViewModel
) {
    var route by remember { mutableStateOf<AuthRoute>(AuthRoute.SignIn) }

    PaddingProviderScreen {
        when (route) {
            AuthRoute.SignIn -> {
                LoginScreen(
                    onSignUpClick = {
                        route = AuthRoute.SignUp
                    },
                    viewModel = viewModel
                )
            }

            AuthRoute.SignUp -> {
                RegistrationScreen(
                    onSignInClick = { route = AuthRoute.SignIn },
                    viewModel = viewModel
                )
            }
        }
    }
}