package com.sidor.procuts.ui.screens.auth

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.wellness.auth.AuthData
import com.example.wellness.auth.AuthMessageNotifier
import com.example.wellness.auth.AuthUiState
import com.sidor.procuts.R
import com.sidor.procuts.ui.components.MyStyledTextField
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.ui.viewmodels.AuthViewModel

@Composable
fun LoginScreen(
    onSignUpClick: () -> Unit,
    viewModel: AuthViewModel
) {
    val uiState by remember { mutableStateOf(AuthUiState()) }
    val ctx = LocalContext.current
    val messageNotifier = remember { AuthMessageNotifier(ctx) }

    DefaultAuthScreen(
        bottomButtonText = stringResource(R.string.sign_in),
        onBottomButtonClick = {
            viewModel.signIn(
                AuthData(
                    uiState.email,
                    uiState.password
                )
            ) { status ->
                messageNotifier.notifyUser(status)
            }
        }
    ) {
        Text(
            text = stringResource(R.string.login),
            style = MaterialTheme.typography.headlineLarge,
        )

        DefaultSpacer(2)

        MyStyledTextField(
            value = uiState.email,
            onValueChange = { uiState.email = it },
            label = stringResource(R.string.email),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = uiState.emailIsValidated
        )

        DefaultSpacer(1)

        MyStyledTextField(
            value = uiState.password,
            onValueChange = { uiState.password = it },
            label = stringResource(R.string.password),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = uiState.passwordIsValidated
        )

        DefaultSpacer(1)

        TextButton(
            onClick = onSignUpClick,
        ) {
            Text(
                text = stringResource(R.string.have_not_account),
                style = MaterialTheme.typography.bodyMedium,
                color = LocalColorPalette.current.darkFontColor
            )
        }
    }
}