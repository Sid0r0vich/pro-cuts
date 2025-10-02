package com.sidor.procuts.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.sidor.procuts.ui.navigation.MainRoute
import com.sidor.procuts.ui.screens.auth.AuthScreen
import com.sidor.procuts.ui.screens.main.MainScreen
import com.sidor.procuts.ui.screens.state.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun MyApp() {
    var authState by remember { mutableStateOf<Boolean?>(null) }

    LaunchedEffect(Unit) {
        delay(2000)
        authState = false
    }

    when (authState) {
        null -> {
            LoadingScreen()
        }
        false -> {
            AuthScreen(
                onSignUpClick = { authState = true }
            )
        }
        true -> {
            MainScreen()
        }
    }
}