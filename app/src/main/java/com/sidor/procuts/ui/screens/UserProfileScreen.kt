package com.sidor.procuts.ui.screens

import androidx.compose.runtime.Composable
import com.sidor.procuts.data.defaultPersonDTO
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.screens.cards.PersonCard
import com.sidor.procuts.ui.screens.topbars.UserTopAppBar

@Composable
fun UserProfileScreen() {
    TopAppBarScreen(
        topBar = { UserTopAppBar() },
    ) {
        PaddingScreen(
            paddingSpaces = PaddingSpaces(2)
        ) {
            PersonCard(defaultPersonDTO)
        }
    }
}