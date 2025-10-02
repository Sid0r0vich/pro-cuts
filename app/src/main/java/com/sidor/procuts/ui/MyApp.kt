package com.sidor.procuts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wellness.auth.AuthState
import com.sidor.procuts.ui.screens.auth.AuthScreen
import com.sidor.procuts.ui.screens.main.MainScreen
import com.sidor.procuts.ui.screens.state.ErrorScreen
import com.sidor.procuts.ui.screens.state.LoadingScreen
import com.sidor.procuts.ui.viewmodels.AuthViewModel
import androidx.compose.runtime.getValue

@Composable
fun MyApp(
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authState by viewModel.uiState.collectAsState()

    when (authState) {
        AuthState.Loading -> {
            LoadingScreen()
        }
        AuthState.Unauthenticated -> {
            AuthScreen(
                viewModel = viewModel,
            )
        }
        AuthState.Authenticated -> {
            MainScreen()
        }

        is AuthState.Error -> {
            ErrorScreen(
                errorMessage = "Unknown error",
                onRetry = { }
            )
        }
    }
}