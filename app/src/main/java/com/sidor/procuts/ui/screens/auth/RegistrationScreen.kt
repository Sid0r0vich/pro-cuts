package com.sidor.procuts.ui.screens.auth

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.MyStyledTextField
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.screens.PaddingProviderScreen
import com.sidor.procuts.ui.screens.PaddingScreenWithBottomButtons
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun RegistrationScreen(
    onSignUpClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isNameError by remember { mutableStateOf(false) }
    val isEmailError by remember { mutableStateOf(false) }
    val isPasswordError by remember { mutableStateOf(false) }

    PaddingProviderScreen {
        PaddingScreenWithBottomButtons(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            paddingSpaces = PaddingSpaces(2),
            buttons = {
                Button(
                    onClick = onSignUpClick,
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColorPalette.current.buttonColor
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = stringResource(R.string.register))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }
        ) {
            Text(
                text = stringResource(R.string.registration),
                style = MaterialTheme.typography.headlineLarge,
            )

            DefaultSpacer(2)

            MyStyledTextField(
                value = name,
                onValueChange = { name = it },
                label = stringResource(R.string.name),
                isError = isNameError
            )

            DefaultSpacer(1)

            MyStyledTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.email),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = isEmailError
            )

            DefaultSpacer(1)

            MyStyledTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.password),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = isPasswordError
            )
        }
    }
}