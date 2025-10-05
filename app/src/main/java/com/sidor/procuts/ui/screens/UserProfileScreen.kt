package com.sidor.procuts.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.defaultPersonDTO
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.PersonCard
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.ui.viewmodels.UserProfileViewModel

@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    TopAppBarScreen(
        topBar = { UserTopAppBar() },
    ) {
        PaddingScreenWithBottomButtons(
            paddingSpaces = PaddingSpaces(2),
            buttons = {
                Button(
                    onClick = viewModel::signOut,
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
            PersonCard(defaultPersonDTO)
        }
    }
}