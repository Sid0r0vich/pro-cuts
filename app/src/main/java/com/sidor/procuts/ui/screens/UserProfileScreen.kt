package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.data.models.PersonDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.PersonCard
import com.sidor.procuts.ui.screens.topbars.DefaultTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun UserProfileScreen(
    onEditClick: () -> Unit,
    onSignOut: () -> Unit,
    personDTO: PersonDTO
) {
    TopAppBarScreen(
        topBar = {
            DefaultTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.user_profile_tab_app_bar),
                        style = MaterialTheme.typography.headlineSmall,
                        color = LocalColorPalette.current.mainColor
                    )
                },
                actions = {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = stringResource(R.string.button_back),
                            tint = LocalColorPalette.current.mainColor
                        )
                    }
                },
            )
        },
    ) {
        PaddingScreenWithBottomButtons(
            paddingSpaces = PaddingSpaces(2),
            buttons = {
                Button(
                    onClick = onSignOut,
                    shape = RectangleShape,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LocalColorPalette.current.buttonColor
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = stringResource(R.string.sign_out))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                            modifier = Modifier.padding(start = 4.dp),
                        )
                    }
                }
            }
        ) {
            PersonCard(personDTO)
        }
    }
}