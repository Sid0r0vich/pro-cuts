package com.sidor.procuts.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.models.PersonDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.PersonCard
import com.sidor.procuts.ui.screens.topbars.DefaultTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

@Composable
fun EditUserProfileScreen(
    onBack: () -> Unit,
    personDTO: PersonDTO
) {
    TopAppBarScreen(
        topBar = {
            DefaultTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.edit_user_profile_tab_app_bar),
                        style = MaterialTheme.typography.headlineSmall,
                        color = LocalColorPalette.current.mainColor
                    )
                },
                actions = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.button_back),
                            tint = LocalColorPalette.current.mainColor
                        )
                    }
                },
            )
        },
    ) {
        PaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            PersonCard(personDTO)
        }
    }
}